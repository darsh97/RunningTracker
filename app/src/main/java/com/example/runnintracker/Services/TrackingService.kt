package com.example.runnintracker.Services

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.example.runnintracker.others.Constants
import com.example.runnintracker.others.TrackingEvent
import timber.log.Timber

class TrackingService : LifecycleService() {

    companion object {
        var runState: MutableLiveData<TrackingEvent> = MutableLiveData<TrackingEvent>()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                Constants.ACTION_START_OR_RESUME_SERVICE ->
                    runState.postValue(TrackingEvent.START_OR_RESUME_RUN)
                Constants.ACTION_PAUSE_SERVICE ->
                    runState.postValue(TrackingEvent.PAUSE_RUN)
                Constants.ACTION_STOP_SERVICE ->
                    runState.postValue(TrackingEvent.STOP_RUN)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

}