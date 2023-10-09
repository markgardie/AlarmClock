package com.example.alarmclock.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.alarmclock.ui.screens.AlarmListRoute
import com.example.alarmclock.ui.viewmodels.AlarmViewModel

const val alarmListRoute = "alarm_list"

fun NavController.navigateToAlarmList(navOptions: NavOptions? = null) {
    this.navigate(alarmListRoute, navOptions)
}

fun NavGraphBuilder.alarmListScreen(
    navigateToUpsert: (Int) -> Unit,
    viewModel: AlarmViewModel
) {
    composable(
        route = alarmListRoute
    ) {

        AlarmListRoute(
            navigateToUpsert = navigateToUpsert,
            viewModel = viewModel
        )
    }
}
