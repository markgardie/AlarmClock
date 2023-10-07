package com.example.alarmclock.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.alarmclock.domain.AlarmItem
import com.example.alarmclock.ui.navigation.DEFAULT_ALARM_ID
import com.example.alarmclock.ui.viewmodels.AlarmUiState
import com.example.alarmclock.ui.viewmodels.AlarmViewModel
import kotlinx.coroutines.delay


@Composable
fun AlarmListRoute(
    viewModel: AlarmViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
    navigateToUpsert: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AlarmListScreen(
        alarmUiState = uiState,
        navigateToUpsert = navigateToUpsert,
        onSwitchChange = viewModel::updateAlarm,
        onDeleteAlarm = viewModel::deleteAlarm,
        modifier = modifier
    )

}

@Composable
fun AlarmListScreen(
    alarmUiState: AlarmUiState,
    navigateToUpsert: (Int) -> Unit,
    onSwitchChange: (AlarmItem) -> Unit,
    onDeleteAlarm: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    when (alarmUiState) {
        is AlarmUiState.Success -> AlarmsList(
            modifier = modifier,
            alarms = alarmUiState.alarms,
            onSwitchChange = onSwitchChange,
            onDeleteAlarm = onDeleteAlarm,
            navigateToUpsert = navigateToUpsert
        )

        is AlarmUiState.Empty -> EmptyState(modifier = modifier)

        is AlarmUiState.Loading -> LoadingState(modifier = modifier)

    }

    Box(contentAlignment = Alignment.BottomEnd) {
        FloatingActionButton(
            modifier = modifier.padding(16.dp),
            onClick = { navigateToUpsert(DEFAULT_ALARM_ID) }) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new alarm")
        }
    }

}

@Composable
fun AlarmsList(
    modifier: Modifier = Modifier,
    onSwitchChange: (AlarmItem) -> Unit,
    onDeleteAlarm: (Int) -> Unit,
    alarms: List<AlarmItem>,
    navigateToUpsert: (Int) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(alarms) {
            AlarmDismissItem(
                alarm = it,
                onSwitchChange = onSwitchChange,
                onDeleteAlarm = onDeleteAlarm,
                navigateToUpsert = navigateToUpsert
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmDismissItem(
    onSwitchChange: (AlarmItem) -> Unit,
    onDeleteAlarm: (Int) -> Unit,
    alarm: AlarmItem,
    navigateToUpsert: (Int) -> Unit
) {

    var show by remember { mutableStateOf(true) }
    val currentItem by rememberUpdatedState(alarm)
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                show = false
                true
            } else false
        }, positionalThreshold = { 150.dp.toPx()  }
    )

    AnimatedVisibility(
        show,exit = fadeOut(spring())
    ) {
        SwipeToDismiss(
            state = dismissState,
            background = {
                DismissBackground()
            },
            dismissContent = {
                AlarmCard(
                    onSwitchChange = onSwitchChange,
                    alarm = alarm,
                    navigateToUpsert = navigateToUpsert
                )
            }
        )
    }


    LaunchedEffect(show) {
        if (!show) {
            delay(800)
            onDeleteAlarm(currentItem.id)
        }
    }

}

@Composable
fun AlarmCard(
    modifier: Modifier = Modifier,
    onSwitchChange: (AlarmItem) -> Unit,
    alarm: AlarmItem,
    navigateToUpsert: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable {
                navigateToUpsert(alarm.id)
            }
    ) {
        Row(
            modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier.padding(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "${alarm.hours}:${alarm.minutes}",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = alarm.title,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            var checked by remember {
                mutableStateOf(alarm.enabled)
            }

            Switch(modifier = modifier.padding(8.dp), checked = checked, onCheckedChange = {
                checked = it
                onSwitchChange.invoke(alarm.copy(enabled = it))
            })
        }
    }
}


@Composable
fun DismissBackground() {


    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(
            Icons.Default.Delete,
            contentDescription = "delete"
        )

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

