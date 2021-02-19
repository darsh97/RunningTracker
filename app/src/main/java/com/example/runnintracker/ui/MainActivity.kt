package com.example.runnintracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.runnintracker.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

// 43d29ada

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_nav)
        bottomNav.setupWithNavController(navHostFragment.findNavController())
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_run -> {
                    navHostFragment.findNavController().navigate(R.id.runFragment)
                }

                R.id.nav_stats -> {
                    navHostFragment.findNavController().navigate(R.id.statisticsFragment)
                }

                R.id.nav_settings -> {
                    navHostFragment.findNavController().navigate(R.id.settingsFragment)
                }
            }
            true
        }

        navHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                        bottomNav.visibility = View.VISIBLE
                    else -> bottomNav.visibility = View.GONE
                }
            }

    }
}