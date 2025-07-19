package com.example.quizapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun LeaderboardScreen(navController: NavController) {
    val context = LocalContext.current
    val dbHelper = remember { LeaderboardDBHelper(context) }
    val scores = remember { dbHelper.getTopScores() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🏆 Leaderboard", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(scores) { score ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(score.name)
                    Text(score.score.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("start") }) {
            Text("Play Again")
        }
    }
}