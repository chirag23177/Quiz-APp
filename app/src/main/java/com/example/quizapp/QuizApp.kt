package com.example.quizapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizapp.ui.screens.*

@Composable
fun QuizApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            StartScreen(navController)
        }
        composable(
            route = "quiz/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("userName") ?: ""
            QuizScreen(navController, name)
        }
        composable(
            route = "result/{userName}/{score}",
            arguments = listOf(
                navArgument("userName") { type = NavType.StringType },
                navArgument("score") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("userName") ?: ""
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            ResultScreen(navController, name, score)
        }
        composable("leaderboard") {
            LeaderboardScreen(navController)
        }
    }
}