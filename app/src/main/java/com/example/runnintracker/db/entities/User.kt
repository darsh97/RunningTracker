package com.example.runnintracker.db.entities

data class User(
    var userName: String = "",
    var userAge: Int = 0,
    var userHeight: Float = 0F,
    var userWeight: Float = 0F
)