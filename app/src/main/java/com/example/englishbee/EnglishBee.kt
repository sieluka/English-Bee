package com.example.englishbee

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.englishbee.screens.ScreenOne
import com.example.englishbee.screens.ScreenThree
import com.example.englishbee.screens.ScreenTwo


@Composable
fun NavigationContent() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "screen1") {
        composable("screen1") {
            ScreenOne(
                onNavigateToScreen2 = { navController.navigate("screen2") },
            )
        }
        composable("screen2") {
            ScreenTwo(
                onNavigateToScreen1 = { navController.navigate("screen1") },
                onNavigateToScreen3 = { navController.navigate("screen3") }
            )
        }
        composable("screen3") {
            ScreenThree(
                onNavigateToScreen2 = { navController.navigate("screen2") },
                onNavigateToScreen1 = { navController.navigate("screen1") }
            )
        }
    }
}