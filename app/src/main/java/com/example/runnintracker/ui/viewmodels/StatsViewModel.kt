package com.example.runnintracker.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runnintracker.repository.RunRepository

//TODO inject Run Reposiory
class StatsViewModel @ViewModelInject constructor(val runRepository: RunRepository) : ViewModel() {
}