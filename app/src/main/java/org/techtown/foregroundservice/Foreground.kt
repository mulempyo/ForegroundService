package org.techtown.foregroundservice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class Foreground : Service() {

    val CHANNEL_ID = "FG5153"

    override fun onBind(intent: Intent): IBinder {
        return Binder()
    }
    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1) run {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Forground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        createNotificationChannel()

        val notification:Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Forground Service ")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .build()

        startForeground(1,notification)
        return super.onStartCommand(intent, flags, startId)
    }
}