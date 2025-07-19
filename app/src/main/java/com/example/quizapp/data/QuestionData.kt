package com.example.quizapp.data

object QuestionData {
    fun getQuestions(): List<Question> = listOf(
        Question(
            id = 1,
            questionText = "What is the capital of France?",
            option1 = "Berlin",
            option2 = "Madrid",
            option3 = "Paris",
            option4 = "Rome",
            correctAnswer = 3
        ),
        Question(
            id = 2,
            questionText = "What is the largest planet in our solar system?",
            option1 = "Earth",
            option2 = "Jupiter",
            option3 = "Saturn",
            option4 = "Mars",
            correctAnswer = 2
        ),
        Question(
            id = 3,
            questionText = "What is the chemical symbol for water?",
            option1 = "H2O",
            option2 = "CO2",
            option3 = "O2",
            option4 = "NaCl",
            correctAnswer = 1
        ),
        Question(
            id = 4,
            questionText = "Who wrote 'To Kill a Mockingbird'?",
            option1 = "Harper Lee",
            option2 = "Mark Twain",
            option3 = "Ernest Hemingway",
            option4 = "F. Scott Fitzgerald",
            correctAnswer = 1
        )
    )
}