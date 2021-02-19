package com.example.runnintracker.others

sealed class TrackingEvent {
    object START_OR_RESUME_RUN : TrackingEvent()
    object PAUSE_RUN : TrackingEvent()
    object STOP_RUN : TrackingEvent()
}