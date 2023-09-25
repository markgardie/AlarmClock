package com.example.alarmclock.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AlarmNavHost() {
    val navController = rememberNavController()
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
                onAddButtonClick = navController::navigateToAddAlarm
            )

            addAlarmScreen(
                onAddButtonClick = navController::navigateToAlarmList
            )
        }

    }

}