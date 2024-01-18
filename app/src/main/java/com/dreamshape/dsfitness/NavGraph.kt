package com.dreamshape.dsfitness

import CompleteProfileScreen
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dreamshape.dsfitness.screens.OnboardingScreen
import com.dreamshape.dsfitness.screens.RegistrationScreen
import com.dreamshape.dsfitness.screens.SuccessScreen
import com.dreamshape.dsfitness.screens.WelcomeScreen

object Destinations {
    const val WelcomeScreen = "welcome"
    const val OnboardingScreen = "onboarding"
    const val RegistrationScreen = "registration"
    const val SuccessScreen = "success/{firstName}"
    const val CompleteProfileScreen = "completeProfile"
}

@Composable
fun DSFitnessNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.WelcomeScreen) {
        composable(Destinations.WelcomeScreen) {
            WelcomeScreen(onGetStartedClick = { navController.navigate(Destinations.OnboardingScreen) })
        }
        composable(Destinations.OnboardingScreen) {
            OnboardingScreen(navController = navController) // Pass the NavController here
        }
        composable(Destinations.RegistrationScreen) {
            RegistrationScreen(viewModel = viewModel(), navController) // Pass the viewModel instance
        }
        composable(
            route = Destinations.SuccessScreen,
            arguments = listOf(navArgument("firstName") { type = NavType.StringType })
        ) { backStackEntry ->
            val firstName = backStackEntry.arguments?.getString("firstName") ?: ""
            SuccessScreen(userName = firstName, navController = navController)
        }
        composable(Destinations.CompleteProfileScreen) {
            CompleteProfileScreen() // Ensure this is the correct function from RegisterCont.kt
        }
    }
}

