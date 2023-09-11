package com.example.alarmclock.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarmclock.domain.AlarmItem
import com.example.alarmclock.domain.AlarmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmViewModel @Inject constructor(
    private val repository: AlarmRepository
): ViewModel() {

    val uiState: StateFlow<AlarmUiState> = repository.getAlarms()
        .map {
            if (it.isEmpty()) AlarmUiState.Empty
            else AlarmUiState.Success(it)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AlarmUiState.Loading)


    fun addAlarm(alarmItem: AlarmItem) {
        viewModelScope.launch {
            repository.addAlarm(alarmItem)
        }

    }

    fun deleteAlarm(alarmId: Int) {
        viewModelScope.launch {
            repository.deleteAlarm(alarmId)
        }

    }

    fun updateAlarm(alarmItem: AlarmItem) {
        viewModelScope.launch {
            repository.updateAlarm(alarmItem)
        }
    }

}

sealed class AlarmUiState {

    object Loading: AlarmUiState()

    data class Success(val alarms: List<AlarmItem>): AlarmUiState()

    object Empty: AlarmUiState()

}