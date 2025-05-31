package com.example.englishbee
import com.example.englishbee.screens.VerbQuizScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.englishbee.screens.ScreenOne
import com.example.englishbee.screens.ScreenThree
import com.example.englishbee.screens.ScreenTwo
import com.example.englishbee.screens.GrammarQuizScreen
import com.example.englishbee.screens.LoginPage
import com.example.englishbee.screens.RegisterPage
import com.example.englishbee.screens.VocabularyScreen


@Composable
fun NavigationContent() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "screen1") {

        composable("login") {
            LoginPage(
                onLoggedIn      = { navController.navigate("screen2") },
                onRegisterClick = { navController.navigate("register") }
            )
        }


        composable("screen1") {
            ScreenOne(
                onNavigateToScreen2 = { navController.navigate("login") },
            )
        }
        composable("screen2") {
            ScreenTwo(
                onNavigateToGrammar = { navController.navigate("grammar") },
                onNavigateToVerbs = { navController.navigate("verbs") },
                //onNavigateToScreen1 = { navController.navigate("screen1") },
                onNavigateToDictionary = { navController.navigate("screen3") },
                onNavigateToVocabulary = { navController.navigate("vocabulary") }

            )
        }
        composable("screen3") {
            ScreenThree(
                onNavigateToScreen2 = { navController.navigate("screen2") },
                onNavigateToScreen1 = { navController.navigate("screen1") }
            )
        }
        composable("grammar") {
            GrammarQuizScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable("verbs") {
            VerbQuizScreen(onBack = { navController.popBackStack() })
        }
        composable("vocabulary") {
            VocabularyScreen(
                onBack = { navController.popBackStack() }
            )
        }


        composable("register") {
            RegisterPage(
                onRegistered  = { navController.popBackStack() }, // wróć do login
                onBackToLogin = { navController.popBackStack() }
            )
        }

    }
}