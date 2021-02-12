package com.example.runnintracker.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.runnintracker.db.entities.RunData

@Dao
interface RunDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: RunData)

    @Delete
    suspend fun deleteRun(run: RunData)

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC")
    fun getAllRunSortedByDate(): LiveData<List<RunData>>

    @Query("SELECT * FROM running_table ORDER BY avgSpeedInKm DESC")
    fun getAllRunSortedByAvgSpeed(): LiveData<List<RunData>>

    @Query("SELECT * FROM running_table ORDER BY distanceInMeters DESC")
    fun getAllRunSortedByDistance(): LiveData<List<RunData>>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurned DESC")
    fun getAllRunSortedByCaloriesBurned(): LiveData<List<RunData>>

    @Query("SELECT SUM(timeInMillis) FROM running_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM running_table")
    fun getTotalCaloriesBurned(): LiveData<Int>

    @Query("SELECT AVG(avgSpeedInKm) FROM running_table")
    fun getAverageSpeedInKm(): LiveData<Float>


}
