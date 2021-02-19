package com.example.runnintracker.ui.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.runnintracker.R
import com.example.runnintracker.Services.TrackingService
import com.example.runnintracker.db.entities.User
import com.example.runnintracker.others.Constants
import com.example.runnintracker.others.SessionManagement
import com.example.runnintracker.others.TrackingUtility
import com.example.runnintracker.ui.viewmodels.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.util.jar.Manifest
import javax.inject.Inject

@AndroidEntryPoint
class RunFragment : Fragment(R.layout.fragment_run), EasyPermissions.PermissionCallbacks {

    @Inject
    lateinit var sessionManagement: SessionManagement

    lateinit var currentUser: User

    private val viewModel: MainViewModel by viewModels() // to get our run data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManagement = SessionManagement(requireContext().applicationContext)
        currentUser = sessionManagement.getSession()
        sessionManagement.IncTimesOpened()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissionFromUser()
        val startNewRunBtn: FloatingActionButton = view.findViewById(R.id.fbtn_add_new_run)
        if (sessionManagement.getTimesOpened() == 1) {
            Toast.makeText(
                requireContext(),
                "Welcome ${currentUser.userName} :) ",
                Toast.LENGTH_LONG
            ).show()
        }
        println(sessionManagement.getTimesOpened())
        startNewRunBtn.setOnClickListener {
            findNavController().navigate(R.id.action_runFragment_to_trackingFragment)
        }

        val introText: TextView = view.findViewById(R.id.tview_intro)


        introText.setText("Welcome ${currentUser.userName}, We are under construction. Your data will be displayed here!")
    }


    private fun requestPermissionFromUser() {
        if (TrackingUtility.hasLocationPermissions(requireContext().applicationContext)) {
            return
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "Please accept location permission to use this app",
                Constants.REQUEST_CODE_LOCATION_PERMISSION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "Please accept location permission to use this app",
                Constants.REQUEST_CODE_LOCATION_PERMISSION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else {
            requestPermissionFromUser()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}