package com.example.codingconquerer.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Switch
import android.widget.Toast
import com.example.codingconquerer.data.Datasource
import com.example.codingconquerer.R
import com.example.codingconquerer.adapters.LessonAdapter
import com.example.codingconquerer.models.Lesson

class LessonListActivity : AppCompatActivity() {

    val TAG = this@LessonListActivity.toString()

    lateinit var sharedPref: SharedPreferences
    var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)
    }

    override fun onStart() {
        super.onStart()

        sharedPref = getSharedPreferences("CODING-CONQUERER-PREF", MODE_PRIVATE)
        val datasource = Datasource.getInstance()

        val swEnforceSeqProg = findViewById<Switch>(R.id.swEnforceSeqProg)

        // setting adapter
        val lvLessonList = findViewById<ListView>(R.id.lvLessonList)
        val adapter = LessonAdapter(this, datasource.lessonList)
        lvLessonList.adapter = adapter

        lvLessonList.setOnItemClickListener{ adapterView, view, i, l ->
            Log.d(TAG, "Lesson ${i+1} is clicked.")

            // the first lesson [i=0] does not require checking
            if (swEnforceSeqProg.isChecked && i > 0) {
                val isPrerequisiteDone = sharedPref.getBoolean("PREF_COMPLETE_LESSON_${i}", false)
                if (!isPrerequisiteDone) {
                    // responsive toast replacing the old one
                    toast?.cancel()
                    toast = Toast.makeText(applicationContext,
                        "Lesson $i must be finished before studying Lesson ${i+1} under sequential progression mode.",
                        Toast.LENGTH_SHORT)
                    toast!!.show()

                    Log.e(TAG, "Entry to Lesson ${i+1} has been rejected as prerequisite of studying Lesson $i is not fulfilled.")
                    return@setOnItemClickListener
                }
            }

            Log.d(TAG, "Redirecting user to Lesson Details [lessonNum:${i+1}].")

            // send the corresponding clicked Lesson object to Lesson Details screen
            val intent = Intent(this, LessonDetailActivity::class.java)

            val lesson: Lesson = datasource.lessonList[i]
            intent.putExtra("EXTRA_LESSON_ENTERED", lesson)
            startActivity(intent)
        }
    }
}


