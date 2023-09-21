package com.example.alarmclock.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    viewModel: AlarmViewModel = hiltViewModel(), modifier: Modifier = Modifier
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
    onAddAlarm: (AlarmItem) -> Unit,
    onDeleteAlarm: (Int) -> Unit,
    onUpdateAlarm: (AlarmItem) -> Unit,
    modifier: Modifier = Modifier
) {

    when (alarmUiState) {
        is AlarmUiState.Success -> AlarmsList(
            modifier = modifier,
            alarmUiState.alarms,
            onDeleteAlarm,
            onUpdateAlarm
        )

        is AlarmUiState.Empty -> EmptyState(modifier = modifier)

        is AlarmUiState.Loading -> LoadingState(modifier = modifier)

    }

    Box(contentAlignment = Alignment.BottomEnd) {
        FloatingActionButton(
            modifier = modifier.padding(16.dp),
            onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new alarm")
        }
    }

}

@Composable
fun AlarmsList(
    modifier: Modifier = Modifier,
    alarms: List<AlarmItem>,
    onDeleteAlarm: (Int) -> Unit,
    onUpdateAlarm: (AlarmItem) -> Unit
) {

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(alarms) {
            AlarmCard(
                alarm = it,
                onDeleteAlarm = onDeleteAlarm,
                onUpdateAlarm = onUpdateAlarm
            )
        }
    }
}


@Composable
fun AlarmCard(
    modifier: Modifier = Modifier,
    alarm: AlarmItem,
    onDeleteAlarm: (Int) -> Unit,
    onUpdateAlarm: (AlarmItem) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = alarm.time, fontSize = 24.sp
                )
                Text(
                    text = alarm.title, fontSize = 14.sp
                )
            }

            var checked by remember {
                mutableStateOf(alarm.enabled)
            }

            Switch(modifier = modifier.padding(8.dp), checked = checked, onCheckedChange = {
                checked = it
                onUpdateAlarm?.invoke(alarm.copy(enabled = it))
            })
        }
    }
}

@Composable
fun EmptyState(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
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
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

