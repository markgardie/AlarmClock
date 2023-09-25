package com.example.alarmclock.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlarmItem(
    val id: Int = UNDEFINED_ID,
    val time: String,
    val title: String,
    val enabled: Boolean
) : Parcelable {

    companion object {
        const val UNDEFINED_ID = -1
    }

}
