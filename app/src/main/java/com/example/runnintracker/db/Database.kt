package com.example.runnintracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.runnintracker.db.entities.RunData

@Database(entities = [RunData::class],  version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RunDatabase: RoomDatabase() {
    abstract fun runDao(): RunDao
}