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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.alarmclock.domain.AlarmItem
import com.example.alarmclock.ui.viewmodels.AlarmViewModel


@Composable
fun AddAlarmRoute(
    viewModel: AlarmViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    onAddButtonClick: () -> Unit
) {

    AddAlarmScreen(
        addAlarm = viewModel::addAlarm,
        modifier = modifier,
        onAddButtonClick = onAddButtonClick
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlarmScreen(
    addAlarm: (AlarmItem) -> Unit,
    onAddButtonClick: () -> Unit,
    modifier: Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        var timePickerState = rememberTimePickerState()
        var title by remember {
            mutableStateOf("")
        }
        
        TimePicker(state = timePickerState)

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        
        Button(onClick = {
            addAlarm(
                AlarmItem(
                    time = "${timePickerState.hour}:${timePickerState.minute}",
                    title = title,
                    enabled = false
                )
            )
            onAddButtonClick()
        }) {
            Text(text = "Add alarm")
        }
    }

}

