package com.example.alarmclock.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.alarmclock.domain.AlarmItem
import com.example.alarmclock.ui.viewmodels.AlarmViewModel


@Composable
fun AddAlarmRoute(
    viewModel: AlarmViewModel = hiltViewModel(), 
    modifier: Modifier = Modifier
) {
    
    AddAlarmScreen(addAlarm = viewModel::addAlarm)
    
}

@Composable
fun AddAlarmScreen(
    addAlarm: (AlarmItem) -> Unit
) {
    
    Text(text = "test")
    
}