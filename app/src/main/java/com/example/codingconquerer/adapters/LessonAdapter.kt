package com.example.codingconquerer.adapters

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.codingconquerer.models.Lesson
import com.example.codingconquerer.R

class LessonAdapter (context: Context, lessonList: List<Lesson>) :
    ArrayAdapter<Lesson>(context, 0, lessonList) {

    lateinit var sharedPref: SharedPreferences

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item_lesson, parent, false)

        val ivLessonNum = view.findViewById<ImageView>(R.id.ivLessonNum)
        val tvLessonTitle = view.findViewById<TextView>(R.id.tvLessonTitle)
        val tvLessonDesc = view.findViewById<TextView>(R.id.tvLessonDesc)
        val ivComplete = view.findViewById<ImageView>(R.id.ivComplete)

        val imgComplete = R.drawable.complete
        sharedPref = context.getSharedPreferences("CODING-CONQUERER-PREF", Context.MODE_PRIVATE)


        val lesson = getItem(position)
        lesson?.let{
            tvLessonTitle.text = it.name
            tvLessonDesc.text = "Length: ${it.length}"
            ivLessonNum.setImageResource(it.imgId)

            // mark completion
            val isCompleted = sharedPref.getBoolean("PREF_COMPLETE_LESSON_${it.lessonNum}", false)
            if (isCompleted) {
                ivComplete.setImageResource(imgComplete)
            } else {
                ivComplete.setImageResource(android.R.color.transparent)
            }
        }

        return view
    }
}