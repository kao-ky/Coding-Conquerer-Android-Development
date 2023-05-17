package com.example.codingconquerer.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import com.example.codingconquerer.data.Datasource
import com.example.codingconquerer.R


class WelcomeBackActivity : AppCompatActivity() {
    lateinit var sharedPrefs: SharedPreferences
    val datasource = Datasource.getInstance()
    var TAG = "MY_APP"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_back)

        sharedPrefs = getSharedPreferences("CODING-CONQUERER-PREF", MODE_PRIVATE)

        // val TAG = this@WelcomeBackActivity.toString()
        Log.d(TAG, "You are in WelcomeBack Screen.")

        val getname = sharedPrefs.getString("PREF_USER_NAME", null)

        if (getname.isNullOrEmpty()) {
            intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Initialize UI elements
        val welcomeMessage: TextView = findViewById(R.id.tvWelcome_message)
        val progressMessage: TextView = findViewById(R.id.tvprogress_message)
        val Lesson_ProgressTextView: TextView = findViewById(R.id.tvLesson_Progress)
        val ContinueButton: Button = findViewById(R.id.btnContinue)
        val resetButton: Button = findViewById(R.id.btnReset)
        val completedLessonCount = getCompletedCount()
        val totalLessonCount = datasource.lessonList.size


        welcomeMessage.text = "Welcome Back, ${getname}"

        progressMessage.text = "You've completed ${(completedLessonCount*100)/totalLessonCount}% of the course!"

        Lesson_ProgressTextView.text = "Lesson completed: $completedLessonCount \nLesson remaining:  ${totalLessonCount - completedLessonCount}"

        // Set up button click listener to navigate to lesson list screen
        ContinueButton.setOnClickListener {

            intent = Intent(this, LessonListActivity::class.java)
            startActivity(intent)

        }

        // Set up reset button click listener to delete user data and navigate to Enter Your Name screen
        resetButton.setOnClickListener {
            // Delete any saved data associated with the current user
            sharedPrefs.edit().clear().apply()

            val toast = Toast.makeText(applicationContext, "Profile Deleted.", Toast.LENGTH_LONG)
            toast.show()

            Log.d(TAG, "Profile Deleted in WelcomeBack Screen.")
            // Navigate the user to the Enter Your Name screen
            intent = Intent(this, NameActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "You are in WelcomeBack Screen.")
    }

    private fun getCompletedCount(): Int {
        val completedLessonPrefix = "PREF_COMPLETE_LESSON_"
        var count: Int = 0
        for (i in 1..datasource.lessonList.size) {
            val lesson = completedLessonPrefix + i
            if (sharedPrefs.getBoolean(lesson, false)) count++
        }
        return count
    }
}


