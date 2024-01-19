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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dreamshape.dsfitness.R
import com.dreamshape.dsfitness.components.TopBar
import com.dreamshape.dsfitness.components.WorkoutOptionCard

@Composable
fun WorkoutPlanScreen() {
    Scaffold(
        topBar = { TopBar(title = "Workout Plan", onBackClick = { /* handle back click */ }, onActionClick = { /* handle action click */ }) },
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
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                WorkoutOptionCard(
                    drawableId = R.drawable.biceps_workout, // Replace with actual resource IDs
                    title = "BICEPS",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                WorkoutOptionCard(
                    drawableId = R.drawable.abs_workout, // Replace with actual resource IDs
                    title = "ABS",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                WorkoutOptionCard(
                    drawableId = R.drawable.leg_workout, // Replace with actual resource IDs
                    title = "LEG",
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun WorkoutPlanScreenPreview() {
    WorkoutPlanScreen()
}