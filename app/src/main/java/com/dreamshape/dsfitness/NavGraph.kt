package com.dreamshape.dsfitness

import CompleteProfileScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dreamshape.dsfitness.screens.HomeScreen
import com.dreamshape.dsfitness.screens.LoginScreen
import com.dreamshape.dsfitness.screens.ManageProfileScreen
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
    const val LoginScreen = "login"
    const val HomeScreen = "home"
}

@RequiresApi(Build.VERSION_CODES.O)
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
            RegistrationScreen(
                viewModel = viewModel(),
                navController
            ) // Pass the viewModel instance
        }
        composable(
            route = Destinations.SuccessScreen,
            arguments = listOf(navArgument("firstName") { type = NavType.StringType })
        ) { backStackEntry ->
            val firstName = backStackEntry.arguments?.getString("firstName") ?: ""
            SuccessScreen(userName = firstName, navController = navController)
        }
        composable(Destinations.CompleteProfileScreen) {
            CompleteProfileScreen(navController) // Ensure this is the correct function from RegisterCont.kt


        }
        composable(Destinations.LoginScreen) {
            // Call your LoginScreen composable here
            LoginScreen(navController = navController)
        }
        composable(Destinations.HomeScreen) {
            HomeScreen(navController = navController) // Call your HomeScreen composable here
        }
        composable("manageProfile") {
            ManageProfileScreen() // Assuming this is the correct composable for the profile screen
        }
    }
}

