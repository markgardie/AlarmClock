package com.example.alarmclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.alarmclock.ui.screens.AlarmListScreen
import com.example.alarmclock.ui.theme.AlarmClockTheme
import com.example.alarmclock.ui.viewmodels.AlarmViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmClockTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel by viewModels<AlarmViewModel>()
                    val uiState by viewModel.uiState.collectAsState()

                    AlarmListScreen(
                        alarmUiState = uiState,
                        onAddAlarm = viewModel::addAlarm,
                        onDeleteAlarm = viewModel::deleteAlarm,
                        onUpdateAlarm = viewModel::updateAlarm
                    )
                }
            }
        }
    }
}

