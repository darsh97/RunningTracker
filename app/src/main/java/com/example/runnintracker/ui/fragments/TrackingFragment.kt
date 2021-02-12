package com.example.runnintracker.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.runnintracker.R
import com.example.runnintracker.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment: Fragment(R.layout.tracking_fragment) {
    private val viewModel: MainViewModel by viewModels()

}