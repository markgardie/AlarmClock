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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.alarmclock.domain.AlarmItem
import com.example.alarmclock.ui.viewmodels.AlarmUiState
import com.example.alarmclock.ui.viewmodels.AlarmViewModel

const val alarmListRoute = "alarm_list"

fun NavController.navigateToAlarmList(navOptions: NavOptions? = null) {
    this.navigate(alarmListRoute, navOptions)
}

fun NavGraphBuilder.alarmListScreen() {
    composable(
        route = alarmListRoute
    ) {

        AlarmListRoute()
    }
}

@Composable
fun AlarmListRoute(
    viewModel: AlarmViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AlarmListScreen(
        alarmUiState = uiState,
        onAddAlarm = viewModel::addAlarm,
        onDeleteAlarm = viewModel::deleteAlarm,
        onUpdateAlarm = viewModel::updateAlarm,
        modifier = modifier
    )

}

@Composable
fun AlarmListScreen(
    alarmUiState: AlarmUiState,
    onAddAlarm: ((AlarmItem) -> Unit)? = null,
    onDeleteAlarm: ((Int) -> Unit)? = null,
    onUpdateAlarm: ((AlarmItem) -> Unit)? = null,
    modifier: Modifier = Modifier
) {

    when(alarmUiState) {
        is AlarmUiState.Success -> AlarmsList(
            modifier = modifier,
            alarmUiState.alarms,
            onDeleteAlarm,
            onUpdateAlarm
        )

        is AlarmUiState.Empty -> EmptyState(modifier = modifier)

        is AlarmUiState.Loading -> LoadingState(modifier = modifier)

    }

}

@Composable
fun AlarmsList(
    modifier: Modifier = Modifier,
    alarms: List<AlarmItem>,
    onDeleteAlarm: ((Int) -> Unit)? = null,
    onUpdateAlarm: ((AlarmItem) -> Unit)? = null
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(alarms) {

        }
    }
}

@Composable
fun AlarmCard(
    modifier: Modifier = Modifier,
    alarm: AlarmItem,
    onDeleteAlarm: ((Int) -> Unit)? = null,
    onUpdateAlarm: ((AlarmItem) -> Unit)? = null
) {

}

@Composable
fun EmptyState(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No Alarms"
        )

    }

}

@Composable
fun LoadingState(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}
