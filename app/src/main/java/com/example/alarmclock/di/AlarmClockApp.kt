package com.example.alarmclock.di

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.alarmclock.data.scheduler.AlarmReceiver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AlarmClockApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                AlarmReceiver.CHANNEL_ID,
                AlarmReceiver.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )

            channel.description = "Basic channel for Alarm Clock"

            notificationManager.createNotificationChannel(channel)

        }
    }
}