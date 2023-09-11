package com.example.alarmclock.domain

import kotlinx.coroutines.flow.Flow

interface AlarmRepository {

    fun getAlarms(): Flow<List<AlarmItem>>

    suspend fun addAlarm(alarm: AlarmItem)

    suspend fun updateAlarm(alarm: AlarmItem)

    suspend fun deleteAlarm(alarmId: Int)

}