package com.dreamshape.dsfitness

import CompleteProfileScreen
import LegWorkoutScree
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
import com.dreamshape.dsfitness.screens.MapScreen
import com.dreamshape.dsfitness.screens.NutritionScreen
import com.dreamshape.dsfitness.screens.OnboardingScreen
import com.dreamshape.dsfitness.screens.RegistrationScreen
import com.dreamshape.dsfitness.screens.SuccessScreen
import com.dreamshape.dsfitness.screens.WelcomeScreen
import com.dreamshape.dsfitness.screens.WorkoutPlanScreen
import com.dreamshape.dsfitness.screens.workout.ABSWorkoutScreen
import com.dreamshape.dsfitness.screens.workout.BicepsWorkoutScreen
import com.dreamshape.dsfitness.screens.workout.ChestWorkoutScreen

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
            OnboardingScreen(navController = navController)
        }
        composable(Destinations.RegistrationScreen) {
            RegistrationScreen(
                viewModel = viewModel(),
                navController
            )
        }
        composable(
            route = Destinations.SuccessScreen,
            arguments = listOf(navArgument("firstName") { type = NavType.StringType })
        ) { backStackEntry ->
            val firstName = backStackEntry.arguments?.getString("firstName") ?: ""
            SuccessScreen(userName = firstName, navController = navController)
        }
        composable(Destinations.CompleteProfileScreen) {
            CompleteProfileScreen(navController)


        }
        composable(Destinations.LoginScreen) {

            LoginScreen(navController = navController)
        }
        composable(Destinations.HomeScreen) {
            HomeScreen(navController = navController)
        }
        composable("manageProfile") {
            ManageProfileScreen(navController)
        }
        composable(Destinations.ChestWorkoutScreen) {
            ChestWorkoutScreen()
        }
        composable(Destinations.ABSWorkoutScreen) {
            ABSWorkoutScreen()
        }
        composable(Destinations.BicepsWorkoutScreen) {
            BicepsWorkoutScreen()
        }
        composable(Destinations.LegWorkoutScreen) {
            LegWorkoutScree()
        }
        composable("workoutPlan") {
            WorkoutPlanScreen(navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("nutritionPlan") {
            NutritionScreen(navController = navController)
        }
        composable("map") {
            MapScreen()
        }
    }
}

