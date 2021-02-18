package com.example.runnintracker.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.runnintracker.R
import com.example.runnintracker.db.entities.User
import com.example.runnintracker.others.SessionManagement
import com.example.runnintracker.ui.MainActivity
import com.example.runnintracker.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_setup.*
import javax.inject.Inject

@AndroidEntryPoint
class SetupFragment : Fragment(R.layout.fragment_setup) {
    val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var sessionManagement: SessionManagement


    override fun onStart() {
        super.onStart()
        checkSession()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManagement = SessionManagement(requireContext().applicationContext)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navButton: Button = view.findViewById(R.id.navigateBtn)
        val username: EditText = view.findViewById(R.id.etext_name)
        val userage: EditText = view.findViewById(R.id.etext_age)
        val userheight: EditText = view.findViewById(R.id.etext_height)
        val userweight: EditText = view.findViewById(R.id.etext_weight)


        navButton.setOnClickListener {
            val userName = username.text.toString().trim()
            val userAge = userage.text.toString().trim().toInt()
            val userHeight = userheight.text.toString().trim().toFloat()
            val userWeight = userweight.text.toString().trim().toFloat()
            login(userName, userAge, userHeight, userWeight)
        }
    }

    private fun checkSession() {
        val currentUser: User = sessionManagement.getSession()
        if (currentUser.userName.isNotEmpty() &&
            currentUser.userAge != -1 &&
            currentUser.userHeight != -1F &&
            currentUser.userWeight != -1F
        ) {
            findNavController().navigate(R.id.action_setupFragment_to_runFragment)
        }
    }

    private fun login(name: String, age: Int, height: Float, weight: Float) {
        val user: User = User(name, age, height, weight)
        sessionManagement.saveSession(user)
        findNavController().navigate(R.id.action_setupFragment_to_runFragment)
    }
}