package com.example.runnintracker.DI

import android.content.Context
import androidx.room.Room
import com.example.runnintracker.db.RunDatabase
import com.example.runnintracker.others.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunDatabase(
        @ApplicationContext applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        RunDatabase::class.java,
        Constants.RunningDatabaseName
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db: RunDatabase) = db.runDao()
}