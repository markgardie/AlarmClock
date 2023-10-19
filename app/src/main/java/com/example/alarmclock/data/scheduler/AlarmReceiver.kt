package com.example.alarmclock.data.scheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.alarmclock.R

class AlarmReceiver: BroadcastReceiver() {

    companion object {

        const val CHANNEL_ID = "alarm_id"
        const val CHANNEL_NAME = "Alarm Clock"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmTitle = intent?.getStringExtra("EXTRA_TITLE") ?: return

        context?.let { context ->

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Alarm Clock")
                .setContentText(alarmTitle)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

            notificationManager.notify(1, notification)
        }
    }
}