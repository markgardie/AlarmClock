package com.example.alarmclock.data.scheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.alarmclock.domain.AlarmItem
import com.example.alarmclock.domain.AlarmScheduler
import java.util.Calendar

class AndroidAlarmScheduler(
    private val context: Context
): AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    private val calendar = Calendar.getInstance()

    override fun schedule(alarmItem: AlarmItem) {

        with(calendar) {
            set(Calendar.HOUR_OF_DAY, alarmItem.hour)
            set(Calendar.MINUTE, alarmItem.minute)
        }

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("EXTRA_TITLE", alarmItem.title)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            PendingIntent.getBroadcast(
                context,
                alarmItem.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )


    }

    override fun cancel(alarmItem: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                alarmItem.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}