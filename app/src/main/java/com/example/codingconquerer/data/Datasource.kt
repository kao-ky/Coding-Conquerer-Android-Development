package com.example.codingconquerer.data

import com.example.codingconquerer.models.Lesson
import com.example.codingconquerer.R

class Datasource {

    companion object {
        @Volatile
        private lateinit var instance: Datasource

        fun getInstance(): Datasource {
            synchronized(this) {
                if (!Companion::instance.isInitialized) {
                    instance = Datasource()
                }
                return instance
            }
        }
    }

    val LESSON_DESC_1 = "In this course, you'll learn JavaScript fundamentals that will be helpful as you dive deeper into more advanced topics."

    val LESSON_DESC_2 = "JavaScript is a lightweight programming language that web developers commonly use to create more dynamic interactions when developing web pages, applications..."

    val LESSON_DESC_3 = "All programming languages have a need for variables, for without them, we wouldn’t be able to get much done! Variables will allow us to keep track of all of data."

    val LESSON_DESC_4 = "Loops offer a quick and easy way to do something repeatedly. This chapter of the JavaScript Guide introduces the different iteration statements available to JavaScript."

    val LESSON_DESC_5 = "Functions are one of the fundamental building blocks in JavaScript. A function in JavaScript is similar to a procedure — a set of statements that performs a task or calculates a value."

    val LESSON_DESC_6 = "with the addition of classes, the creation of hierarchies of objects and the inheritance of properties and their values are much more in line with other object-oriented languages such as Java."


    val URL1: String ="https://youtu.be/_y9oxzTGERs"
    val URL2: String ="https://youtu.be/upDLs1sn7g4"
    val URL3: String ="https://youtu.be/IsG4Xd6LlsM"
    val URL4: String ="https://youtu.be/s9wW2PpJsmQ"
    val URL5: String ="https://youtu.be/N8ap4k_1QEQ"
    val URL6: String ="https://youtu.be/ndzAGJin2ZE"

    val lessonList: MutableList<Lesson> = mutableListOf(
        Lesson(1, "Introduction to the Course", "30 min", LESSON_DESC_1, R.drawable.course_1,URL1),
        Lesson(2, "What is Javascript?", "50 min", LESSON_DESC_2, R.drawable.course_2,URL2),
        Lesson(3, "Variable and Conditionals", "1 hour 5 min", LESSON_DESC_3, R.drawable.course_3,URL3),
        Lesson(4, "Loops", "1 hour 15 min", LESSON_DESC_4, R.drawable.course_4,URL4),
        Lesson(5, "Functions", "1 hour 10 min", LESSON_DESC_5, R.drawable.course_5,URL5),
        Lesson(6, "Classes", "55 min", LESSON_DESC_6, R.drawable.course_6,URL6)
    )
}
