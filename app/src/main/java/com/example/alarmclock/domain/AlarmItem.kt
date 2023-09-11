package com.example.alarmclock.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class AlarmItem(
    val id: Int,
    val time: String,
    val title: String,
    val enabled: Boolean
): Parcelable
