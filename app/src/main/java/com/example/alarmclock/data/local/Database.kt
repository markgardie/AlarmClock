package com.example.alarmclock.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AlarmItemDbModel::class], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {

    abstract fun getAlarmDao(): AlarmDao
}