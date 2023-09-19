package com.example.alarmclock.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alarmclock.domain.AlarmItem
import com.example.alarmclock.ui.viewmodels.AlarmUiState

@Composable
fun AlarmListScreen(
    alarmUiState: AlarmUiState,
    onAddAlarm: ((AlarmItem) -> Unit)? = null,
    onDeleteAlarm: ((Int) -> Unit)? = null,
    onUpdateAlarm: ((AlarmItem) -> Unit)? = null
) {

    when(alarmUiState) {
        is AlarmUiState.Success ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(alarmUiState.alarms) {

                }
            }
        is AlarmUiState.Empty ->
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No Alarms"
                )

            }
        is AlarmUiState.Loading ->
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
    }

}
