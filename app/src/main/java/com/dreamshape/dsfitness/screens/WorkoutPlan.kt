package com.dreamshape.dsfitness.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dreamshape.dsfitness.Destinations
import com.dreamshape.dsfitness.R
import com.dreamshape.dsfitness.components.BottomBar
import com.dreamshape.dsfitness.components.WorkoutOptionCard

@Composable
fun WorkoutPlanScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController) // Include the BottomBar composable here
        },
        content = { PaddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF0F0F0))
                    .padding(PaddingValues)
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                WorkoutOptionCard(
                    drawableId = R.drawable.chest_workout, // Replace with actual resource IDs
                    title = "CHEST",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = { navController.navigate(Destinations.ChestWorkoutScreen) } // Navigate to Chest Workout Screen
                )
                // Placeholder onClick for other cards
                val placeholderOnClick = { /* Placeholder action */ }
                WorkoutOptionCard(
                    drawableId = R.drawable.biceps_workout, // Replace with actual resource IDs
                    title = "BICEPS",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = { navController.navigate(Destinations.BicepsWorkoutScreen) }
                )
                WorkoutOptionCard(
                    drawableId = R.drawable.abs_workout, // Replace with actual resource IDs
                    title = "ABS",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = { navController.navigate(Destinations.ABSWorkoutScreen) }
                )
                WorkoutOptionCard(
                    drawableId = R.drawable.leg_workout, // Replace with actual resource IDs
                    title = "LEG",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = { navController.navigate(Destinations.LegWorkoutScreen) }
                )
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun WorkoutPlanScreenPreview() {
    WorkoutPlanScreen(navController = NavHostController(LocalContext.current))
}