package com.example.alarmclock.domain

import com.example.alarmclock.domain.AlarmItem

interface AlarmScheduler {

    fun schedule(alarmItem: AlarmItem)

    fun cancel(alarmItem: AlarmItem)
}