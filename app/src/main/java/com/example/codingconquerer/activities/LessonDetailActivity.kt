package com.example.codingconquerer.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.example.codingconquerer.R
import com.example.codingconquerer.data.Datasource
import com.example.codingconquerer.models.Lesson

class LessonDetailActivity : AppCompatActivity() {

    private val TAG = this@LessonDetailActivity.toString()
    private val sharedPrefsKey = "CODING-CONQUERER-PREF"
    private val datasource by lazy { Datasource.getInstance() }
    private lateinit var lessonNameTextView: TextView
    private lateinit var lessonLengthTextView: TextView
    private lateinit var lessonDescriptionTextView: TextView
    private lateinit var webView: WebView
    private lateinit var notesEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_detail)

        if (intent == null) {
            Log.e(TAG, "could not find intent")
            finish()
        }

        val sharedPrefs = getSharedPreferences(sharedPrefsKey, Context.MODE_PRIVATE)

        // Check if intent has lesson data
        val serializableObj = intent?.getSerializableExtra("EXTRA_LESSON_ENTERED") as? Lesson
        if (serializableObj == null) {
            Log.e(TAG, "Could not find EXTRA_LESSON_ENTERED in the intent")
            finish()
        }
        Log.d(TAG, "You are in lesson detail Screen.")

        // Set up UI elements
        val lesson = serializableObj as Lesson
        lessonNameTextView = findViewById(R.id.lesson_name_textview)
        lessonLengthTextView = findViewById(R.id.lesson_length_textview)
        lessonDescriptionTextView = findViewById(R.id.lesson_description_textview)
        notesEditText = findViewById(R.id.notes_edittext)
        lessonNameTextView.text = "${lesson.lessonNum}. ${lesson.name}"
        lessonLengthTextView.text = "Length: ${lesson.length}"
        lessonDescriptionTextView.text = lesson.desc

        Log.d(TAG, "${lesson.videoUrl}")
        // Set up WebView
        webView = findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
            }
        }
      val watchLessonButton = findViewById<Button>(R.id.watch_lesson_button)
        webView.loadUrl(lesson.videoUrl)

       watchLessonButton.setOnClickListener {
           val urlToOpenAsUri: Uri = Uri.parse(lesson.videoUrl)
           intent = Intent(Intent.ACTION_VIEW, urlToOpenAsUri)
           if (intent.resolveActivity(packageManager) != null) {
               startActivity(intent)
           }
           else {
               val toast = Toast.makeText(this, "Cannot open website", Toast.LENGTH_SHORT)
               toast.show()
           }

       }

        notesEditText.setText(sharedPrefs.getString("PREF_LESSON_${lesson.lessonNum}_NOTES", ""))

        // Set up Save button click listener to save notes
        val saveButton: Button = findViewById(R.id.save_button)
        saveButton.setOnClickListener {
            Log.d(TAG, "Click the Save Button")
            with(sharedPrefs.edit()) {
                putString("PREF_LESSON_${lesson.lessonNum}_NOTES", notesEditText.text.toString())
                apply()
                Toast.makeText(applicationContext, "Notes saved", Toast.LENGTH_SHORT).show()

            }
        }


        // Set up Mark Complete button click listener to mark the lesson as complete
        val markCompleteButton: Button = findViewById(R.id.mark_complete_button)
        markCompleteButton.setOnClickListener {
            Log.d(TAG, "Click the Mark Button")

            with(sharedPrefs.edit()) {
                putBoolean("PREF_COMPLETE_LESSON_${lesson.lessonNum}", true)
                apply()

                intent = Intent(applicationContext, LessonListActivity::class.java)
                startActivity(intent)

                Toast.makeText(applicationContext, "Your Completed this Lesson", Toast.LENGTH_SHORT)
                    .show()

            }
        }

    }
}


