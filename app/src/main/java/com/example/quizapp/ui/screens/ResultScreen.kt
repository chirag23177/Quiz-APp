package com.example.quizapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quizapp.data.LeaderboardDBHelper
import com.example.quizapp.data.UserScore

@Composable
fun ResultScreen(navController: NavController, userName: String, score: Int) {
    val context = LocalContext.current
    val dbHelper = remember { LeaderboardDBHelper(context) }

    // Add score to DB when composable is first composed
    LaunchedEffect(Unit) {
        dbHelper.addUserScore(UserScore(userName, score))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🎉 Quiz Completed!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text("$userName, your score is $score", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("leaderboard") }) {
            Text("View Leaderboard")
        }
    }
}