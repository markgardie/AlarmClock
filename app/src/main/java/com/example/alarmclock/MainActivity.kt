package com.example.alarmclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.alarmclock.data.scheduler.AndroidAlarmScheduler
import com.example.alarmclock.ui.navigation.AlarmNavHost
import com.example.alarmclock.ui.theme.AlarmClockTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scheduler = AndroidAlarmScheduler(this)
        setContent {
            AlarmClockTheme {
                AlarmNavHost(
                    scheduler::schedule,
                    scheduler::cancel
                )
            }
        }
    }
}

