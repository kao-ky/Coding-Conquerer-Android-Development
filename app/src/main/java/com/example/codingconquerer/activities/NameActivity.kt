package com.example.codingconquerer.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.codingconquerer.R

class NameActivity : AppCompatActivity() {

    val TAG = this@NameActivity.toString()
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        sharedPref = getSharedPreferences("CODING-CONQUERER-PREF", MODE_PRIVATE)
        Log.d(TAG, "Entered Screen <Enter Your Name>.")

        val etNameInput: EditText = findViewById(R.id.etNameInput)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {

            // Validate name input
            val name: String = etNameInput.text.toString()

            if (name.isEmpty()) {
                Log.e("APP-ERR", "Error > Name is null or empty.")
                Toast.makeText(applicationContext, "Please enter your name.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Save user profile & clear fields
            with (sharedPref.edit()) {
                putString("PREF_USER_NAME", name)
                putBoolean("PREF_IS_EXISTING_USER", true)
                apply()
                Log.d(TAG, "User name saved.")
                Toast.makeText(applicationContext, "Profile created.", Toast.LENGTH_LONG).show()
                etNameInput.setText("")
            }

            // Move to Lesson List Screen
            val intent = Intent(this, LessonListActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}