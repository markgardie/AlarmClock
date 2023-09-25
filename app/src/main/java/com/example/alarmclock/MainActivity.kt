package com.example.alarmclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.alarmclock.ui.navigation.AlarmNavHost
import com.example.alarmclock.ui.theme.AlarmClockTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmClockTheme {
                AlarmNavHost()
            }
        }
    }
}

