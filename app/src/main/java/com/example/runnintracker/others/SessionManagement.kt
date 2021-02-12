package com.example.runnintracker.others

import android.content.Context
import android.content.SharedPreferences
import com.example.runnintracker.db.entities.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManagement @Inject constructor(@ApplicationContext context: Context){
    val SHARED_PREF_NAME: String = "session"
    var sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()


    fun saveSession(user: User){
        val userName: String = user.userName
        val userAge: Int = user.userAge
        val userHeight: Float = user.userHeight
        val userWeight: Float = user.userWeight

        editor.putString("name", userName)
        editor.putInt("age", userAge)
        editor.putFloat("height", userHeight)
        editor.putFloat("weight", userWeight)
        editor.commit()

    }

    fun getSession(): User{
        val userName: String? = sharedPreferences.getString("name", "")
        val userAge: Int = sharedPreferences.getInt("age", -1)
        val userHeight: Float = sharedPreferences.getFloat("height", -1F)
        val userWeight: Float = sharedPreferences.getFloat("weight", -1F)

        val currentUser: User = User()

        userName?.let {
            currentUser.userName = userName
        }

        currentUser.userAge = userAge
        currentUser.userHeight = userHeight
        currentUser.userWeight = userWeight
        return currentUser
    }

    fun removeSession(){
        editor.putString("name", "")
        editor.putInt("age", -1)
        editor.putFloat("height", -1F)
        editor.putFloat("weight", -1F)
        editor.commit()

    }
}