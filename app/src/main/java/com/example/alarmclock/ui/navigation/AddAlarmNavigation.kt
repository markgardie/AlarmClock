package com.example.alarmclock.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.alarmclock.ui.screens.AddAlarmRoute

const val addAlarmRoute = "add_alarm"

fun NavController.navigateToAddAlarm(navOptions: NavOptions? = null) {
    this.navigate(addAlarmRoute, navOptions)
}

fun NavGraphBuilder.addAlarmScreen() {
    composable(
        addAlarmRoute
    ) {
        AddAlarmRoute()
    }
}