package com.example.quizapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quizapp.data.QuestionData

@Composable
fun QuizScreen(navController: NavController, userName: String) {
    val questions = remember { QuestionData.getQuestions() }
    var currentIndex by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf(-1) }
    var score by remember { mutableStateOf(0) }

    val question = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Hello, $userName!", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Question ${currentIndex + 1}/${questions.size}", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = question.questionText, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Column {
            listOf(
                question.option1,
                question.option2,
                question.option3,
                question.option4
            ).forEachIndexed { index, option ->
                Button(
                    onClick = { selectedOption = index + 1 },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == index + 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(text = option)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (selectedOption == question.correctAnswer) score++
                if (currentIndex < questions.lastIndex) {
                    currentIndex++
                    selectedOption = -1
                } else {
                    navController.navigate("result/$userName/$score")
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = selectedOption != -1
        ) {
            Text(text = if (currentIndex == questions.lastIndex) "Finish" else "Next")
        }
    }
}