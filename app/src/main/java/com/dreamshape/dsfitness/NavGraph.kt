package com.dreamshape.dsfitness

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dreamshape.dsfitness.screens.OnboardingScreen
import com.dreamshape.dsfitness.screens.RegistrationScreen
import com.dreamshape.dsfitness.screens.WelcomeScreen

object Destinations {
    const val WelcomeScreen = "welcome"
    const val OnboardingScreen = "onboarding"
    const val RegistrationScreen = "registration"
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
            RegistrationScreen(viewModel = viewModel()) // Pass the viewModel instance
        }

    }
}

