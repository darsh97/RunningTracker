package com.example.runnintracker.db.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "running_table")
data class RunData(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    var img: Bitmap? = null,
    var timestamp: Long = 0L,
    var avgSpeedInKm: Float = 0F,
    var distanceInMeters: Int = 0,
    var timeInMillis: Long = 0L,
    var caloriesBurned: Int = 0
)