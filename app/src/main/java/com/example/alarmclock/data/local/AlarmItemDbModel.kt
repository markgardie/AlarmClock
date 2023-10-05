package com.example.alarmclock.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Entity(tableName = "alarms")
data class AlarmItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val hours: String,
    val minutes: String,
    val title: String,
    val enabled: Boolean
)
