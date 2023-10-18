package com.example.alarmclock.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class AlarmItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val hour: Int,
    val minute: Int,
    val title: String,
    val enabled: Boolean
)
