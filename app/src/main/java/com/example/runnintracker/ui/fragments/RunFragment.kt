package com.example.runnintracker.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.runnintracker.R
import com.example.runnintracker.others.SessionManagement
import com.example.runnintracker.ui.viewmodels.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RunFragment: Fragment(R.layout.fragment_run) {

    @Inject
    lateinit var sessionManagement: SessionManagement

    private val viewModel: MainViewModel by viewModels() // to get our run data


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManagement = SessionManagement(requireContext().applicationContext)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val startNewRunBtn: FloatingActionButton = view.findViewById(R.id.fbtn_add_new_run)

        startNewRunBtn.setOnClickListener{
            findNavController().navigate(R.id.action_runFragment_to_trackingFragment2)
        }
    }
}