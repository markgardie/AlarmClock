package com.example.alarmclock.di

import com.example.alarmclock.data.DefaultAlarmRepository
import com.example.alarmclock.domain.AlarmRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module()
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindAlarmRepository(impl: DefaultAlarmRepository): AlarmRepository
}