package com.example.codingconquerer.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.codingconquerer.R

class SplashScreenActivity : AppCompatActivity() {
    var TAG="MY_APP"
    private lateinit var sharedPref: SharedPreferences
    private val SPLASH_TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sharedPref = getSharedPreferences("CODING-CONQUERER-PREF", MODE_PRIVATE)

        val getname =sharedPref.getString("PREF_USER_NAME",null)
        Log.e(TAG, "We are in Splash with ${getname}")
        // Display the splash screen for a few seconds
        Handler().postDelayed({

            val isExistingUser = sharedPref.getBoolean("PREF_IS_EXISTING_USER", false)
            lateinit var intent: Intent

            // if existing user, go to welcome back screen; else, go to enter name screen
            if (isExistingUser) {
                intent = Intent(this, WelcomeBackActivity::class.java)
            } else {
                intent = Intent(this, NameActivity::class.java)
            }

            startActivity(intent)

            // Finish this activity to prevent user from going back to it
            finish()
        }, SPLASH_TIME_OUT)
    }
}




