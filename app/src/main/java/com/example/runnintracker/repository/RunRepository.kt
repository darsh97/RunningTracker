package com.example.runnintracker.repository

import com.example.runnintracker.db.RunDao
import com.example.runnintracker.db.entities.RunData
import javax.inject.Inject


// TODO inject Dager runDao and perform the dao operations
class RunRepository @Inject constructor(val runDao: RunDao){

    suspend fun insertRun(runData: RunData) =  runDao.insertRun(runData)

    suspend fun deletRun(runData: RunData) =  runDao.deleteRun(runData)

    fun getAllRunSortedByDate() = runDao.getAllRunSortedByDate()

    fun getAllRunSortedByDistance() = runDao.getAllRunSortedByDistance()

    fun getAllRunSortedByCaloriesBurned() = runDao.getAllRunSortedByCaloriesBurned()

    fun getAllRunSortedByAvgSpeed() = runDao.getAllRunSortedByAvgSpeed()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    fun getAverageSpeedInKm() = runDao.getAverageSpeedInKm()

}