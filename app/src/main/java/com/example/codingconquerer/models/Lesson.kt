package com.example.codingconquerer.models

import java.io.Serializable

data class Lesson (val lessonNum: Int, val name: String, val length: String, val desc: String, val imgId: Int, val videoUrl: String,val isComplete: Boolean = false ): Serializable