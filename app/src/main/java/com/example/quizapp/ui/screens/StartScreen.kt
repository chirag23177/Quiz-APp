package com.example.quizapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.Composable

@Composable
fun StartScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Welcome to the Quiz!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (name.isNotBlank()) {
                    navController.navigate("quiz/${name.trim()}")
                }
            },
            enabled = name.isNotBlank()
        ) {
            Text("Start Quiz")
        }
    }
}