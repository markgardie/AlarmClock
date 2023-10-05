package com.example.alarmclock.data

import com.example.alarmclock.data.local.AlarmItemDbModel
import com.example.alarmclock.domain.AlarmItem

fun AlarmItemDbModel.asEntity() = AlarmItem(
    id = id,
    title = title,
    hours = hours,
    minutes = minutes,
    enabled = enabled
)

fun AlarmItem.asDbModel() = AlarmItemDbModel(
    id = id,
    title = title,
    hours = hours,
    minutes = minutes,
    enabled = enabled
)