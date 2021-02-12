package com.example.runnintracker.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.runnintracker.R
import com.example.runnintracker.ui.viewmodels.MainViewModel

class SettingsFragment: Fragment(R.layout.fragment_settings) {
    private val viewModel: MainViewModel by viewModels()

}