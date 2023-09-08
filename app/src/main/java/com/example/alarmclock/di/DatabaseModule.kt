package com.example.alarmclock.di

import android.content.Context
import androidx.room.Room
import com.example.alarmclock.data.local.AlarmDao
import com.example.alarmclock.data.local.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "alarm_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideAlarmDao(db: Database): AlarmDao {
        return db.getAlarmDao()
    }
}