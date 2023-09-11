package com.example.alarmclock.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarms")
    fun getAlarms(): Flow<List<AlarmItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlarm(alarmItemDbModel: AlarmItemDbModel)

    @Update
    suspend fun updateAlarm(alarmItemDbModel: AlarmItemDbModel)

    @Query("DELETE FROM alarms WHERE id = :alarmId")
    suspend fun deleteAlarm(alarmId: Int)
}