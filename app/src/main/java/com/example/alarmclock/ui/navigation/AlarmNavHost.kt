package com.example.alarmclock.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.alarmclock.data.scheduler.AndroidAlarmScheduler
import com.example.alarmclock.domain.AlarmItem
import com.example.alarmclock.ui.viewmodels.AlarmViewModel

@Composable
fun AlarmNavHost(
    schedule: (AlarmItem) -> Unit,
    cancel: (AlarmItem) -> Unit
) {
    val navController = rememberNavController()
    val alarmViewModel: AlarmViewModel = hiltViewModel()
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        NavHost(
            navController = navController,
            startDestination = alarmListRoute
        ) {
            alarmListScreen(
                navigateToUpsert = {
                    navController.navigateToUpsertAlarm(it)
                },
                viewModel = alarmViewModel,
                cancel = cancel
            )

            upsertAlarmScreen(
                navigateToList = navController::navigateToAlarmList,
                viewModel = alarmViewModel,
                schedule = schedule
            )
        }

    }

}