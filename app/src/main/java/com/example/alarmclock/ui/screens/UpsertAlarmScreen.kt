package com.example.alarmclock.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.alarmclock.domain.AlarmItem
import com.example.alarmclock.ui.navigation.DEFAULT_ALARM_ID
import com.example.alarmclock.ui.viewmodels.AlarmUiState
import com.example.alarmclock.ui.viewmodels.AlarmViewModel




@Composable
fun UpsertAlarmRoute(
    viewModel: AlarmViewModel,
    modifier: Modifier = Modifier,
    navigateToList: () -> Unit,
    alarmId: Int? = null,
    schedule: (AlarmItem) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val alarms = when(uiState) {
        is AlarmUiState.Success -> (uiState as AlarmUiState.Success).alarms
        else -> emptyList()
    }

    val alarm = parseAlarmItem(alarms, alarmId ?: DEFAULT_ALARM_ID)

    UpsertAlarmScreen(
        upsertAlarm = viewModel::upsertAlarm,
        modifier = modifier,
        navigateToList = navigateToList,
        alarmItem = alarm,
        alarmId = alarmId,
        schedule = schedule
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpsertAlarmScreen(
    upsertAlarm: (AlarmItem) -> Unit,
    navigateToList: () -> Unit,
    modifier: Modifier,
    alarmId: Int?,
    alarmItem: AlarmItem?,
    schedule: (AlarmItem) -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val timePickerState = rememberTimePickerState(
            is24Hour = true,
            initialHour = alarmItem?.hour ?: 0,
            initialMinute = alarmItem?.minute ?: 0
        )
        var titleField by remember {
            mutableStateOf(alarmItem?.title ?: "")
        }
        
        TimePicker(state = timePickerState)

        TextField(
            value = titleField,
            onValueChange = { titleField = it },
            label = { Text("Title") }
        )
        
        Button(onClick = {
            val alarm = AlarmItem(
                id = alarmId ?: DEFAULT_ALARM_ID,
                hour = timePickerState.hour,
                minute = timePickerState.minute,
                title = titleField,
                enabled = false
            )
            upsertAlarm(alarm)
            schedule(alarm)
            navigateToList()
        }) {
            Text(text = "Add alarm")
        }
    }

}

fun parseAlarmItem(list: List<AlarmItem>, id: Int): AlarmItem? {

    for (alarm in list) {
        if (alarm.id == id) {
            return alarm
        }
    }
    return null
}