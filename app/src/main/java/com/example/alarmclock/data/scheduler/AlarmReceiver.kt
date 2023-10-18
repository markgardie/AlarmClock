package com.example.alarmclock.data.scheduler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmTitle = intent?.getStringExtra("EXTRA_TITLE") ?: "null"

        println("Alarm triggered: $alarmTitle")
    }
}