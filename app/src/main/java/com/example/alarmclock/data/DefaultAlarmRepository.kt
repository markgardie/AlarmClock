package com.example.alarmclock.data

import com.example.alarmclock.data.local.AlarmDao
import com.example.alarmclock.data.local.AlarmItemDbModel
import com.example.alarmclock.domain.AlarmItem
import com.example.alarmclock.domain.AlarmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultAlarmRepository @Inject constructor(
    private val alarmDao: AlarmDao
): AlarmRepository {

    override fun getAlarms(): Flow<List<AlarmItem>> =
        alarmDao.getAlarms().map {
            it.map(AlarmItemDbModel::asEntity)
        }

    override suspend fun addAlarm(alarm: AlarmItem) {
        alarmDao.addAlarm(alarm.asDbModel())
    }

    override suspend fun updateAlarm(alarm: AlarmItem) {
        alarmDao.updateAlarm(alarm.asDbModel())
    }
}