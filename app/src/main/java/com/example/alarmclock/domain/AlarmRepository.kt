package com.example.alarmclock.domain

import kotlinx.coroutines.flow.Flow

interface AlarmRepository {

    fun getAlarms(): Flow<List<AlarmItem>>

    suspend fun upsertAlarm(alarm: AlarmItem)

    suspend fun deleteAlarm(alarmId: Int)

}