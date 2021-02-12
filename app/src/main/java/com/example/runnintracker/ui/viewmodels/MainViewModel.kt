package com.example.runnintracker.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runnintracker.repository.RunRepository
import dagger.hilt.android.scopes.ViewModelScoped

// TODO inject dagger RunRepository
class MainViewModel @ViewModelInject constructor(val runRepository: RunRepository): ViewModel(){
}