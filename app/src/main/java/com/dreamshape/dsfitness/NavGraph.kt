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
import com.dreamshape.dsfitness.screens.NutritionScreen
import com.dreamshape.dsfitness.screens.OnboardingScreen
import com.dreamshape.dsfitness.screens.RegistrationScreen
import com.dreamshape.dsfitness.screens.SuccessScreen
import com.dreamshape.dsfitness.screens.WelcomeScreen
import com.dreamshape.dsfitness.screens.WorkoutPlanScreen
import com.dreamshape.dsfitness.screens.workout.ABSWorkoutScreen
import com.dreamshape.dsfitness.screens.workout.BicepsWorkoutScreen
import com.dreamshape.dsfitness.screens.workout.ChestWorkoutScreen
import com.dreamshape.dsfitness.screens.workout.LegWorkoutScreen

object Destinations {
    const val WelcomeScreen = "welcome"
    const val OnboardingScreen = "onboarding"
    const val RegistrationScreen = "registration"
    const val SuccessScreen = "success/{firstName}"
    const val CompleteProfileScreen = "completeProfile"
    const val LoginScreen = "login"
    const val HomeScreen = "home"
    const val ChestWorkoutScreen = "chestWorkout"
    const val ABSWorkoutScreen = "absWorkout"
    const val BicepsWorkoutScreen = "bicepsWorkout"
    const val LegWorkoutScreen = "legWorkout"
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
            ManageProfileScreen(navController) // Assuming this is the correct composable for the profile screen
        }
        composable(Destinations.ChestWorkoutScreen) {
            ChestWorkoutScreen() // Call your ChestWorkoutScreen composable here
        }
        composable(Destinations.ABSWorkoutScreen) {
            ABSWorkoutScreen() // Call your ChestWorkoutScreen composable here
        }
        composable(Destinations.BicepsWorkoutScreen) {
            BicepsWorkoutScreen() // Call your ChestWorkoutScreen composable here
        }
        composable(Destinations.LegWorkoutScreen) {
            LegWorkoutScreen() // Call your ChestWorkoutScreen composable here
        }
        composable("workoutPlan") {
            WorkoutPlanScreen(navController) // Call your ChestWorkoutScreen composable here
        }
        composable("home") {
            HomeScreen(navController = navController) // Call your HomeScreen composable here
        }
        composable("nutritionPlan") {
            NutritionScreen(navController = navController) // Call your HomeScreen composable here
        }
    }
}

