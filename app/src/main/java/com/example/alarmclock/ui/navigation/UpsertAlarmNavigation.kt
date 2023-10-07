package com.example.alarmclock.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.alarmclock.ui.screens.UpsertAlarmRoute


const val ALARM_ID_ARG = "alarmId"
const val DEFAULT_ALARM_ID = 0

const val upsertAlarmRoute = "upsertAlarm/{$ALARM_ID_ARG}"


fun NavController.navigateToUpsertAlarm(alarmId: Int) {
    this.navigate("upsertAlarm/$alarmId")
}

fun NavGraphBuilder.upsertAlarmScreen(
    navigateToList: () -> Unit
) {
    composable(
        route = upsertAlarmRoute,
        arguments = listOf(
            navArgument(ALARM_ID_ARG) {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val alarmId = backStackEntry.arguments?.getInt(ALARM_ID_ARG)

        UpsertAlarmRoute(
            navigateToList = navigateToList,
            alarmId = alarmId,
        )
    }
}