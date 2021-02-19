package com.example.runnintracker.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.runnintracker.R
import com.example.runnintracker.Services.TrackingService
import com.example.runnintracker.others.Constants
import com.example.runnintracker.others.TrackingEvent
import com.example.runnintracker.ui.viewmodels.MainViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tracking_fragment.*

@AndroidEntryPoint
class TrackingFragment : Fragment(R.layout.tracking_fragment) {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var map: GoogleMap
    private lateinit var mapview: MapView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapview = view.findViewById(R.id.map_view)
        val startServiceBtn: Button = view.findViewById(R.id.btn_startRun)
        val stopService: Button = view.findViewById(R.id.btn_stopRun)
        var serviceIsRunning: Boolean = false;

        mapview.onCreate(savedInstanceState)

        mapview.getMapAsync {
            map = it
        }

        // on button click should send action to Lifecycle service
        // life cycle service on receiving action will post event
        // based on that event, we have to toggle stopwatch and other details

        TrackingService.runState.observe(requireActivity(), {
            when (it) {
                is TrackingEvent.START_OR_RESUME_RUN -> {
                    serviceIsRunning = true
                    // deal with UI stats when service has started
                }

                is TrackingEvent.PAUSE_RUN -> {
                    serviceIsRunning = false
                    // service paused
                }

                is TrackingEvent.STOP_RUN -> {
                    serviceIsRunning = false
                    // open dialog with save or discard option and move to run fragment
                }
            }
        })

        startServiceBtn.setOnClickListener {
            sendCommandToTrackingService(Constants.ACTION_START_OR_RESUME_SERVICE)
        }

        stopService.setOnClickListener {
            sendCommandToTrackingService(Constants.ACTION_PAUSE_SERVICE)
        }

        stopService.setOnLongClickListener {
            sendCommandToTrackingService(Constants.ACTION_STOP_SERVICE)
            true
            
        }

    }

    private fun sendCommandToTrackingService(action: String) {
        requireContext().startService(
            Intent(requireContext(), TrackingService::class.java).also {
                it.action = action
            }
        )
    }

    override fun onResume() {
        super.onResume()
        mapview.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapview.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapview.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapview.onDestroy()
    }

}