package com.example.quizapp.data

data class Question (
    val id: Int,
    val questionText: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctAnswer: Int
)