package com.dreamshape.dsfitness.screens.workout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dreamshape.dsfitness.components.ExerciseCard
import com.dreamshape.dsfitness.components.ExerciseHeader

@Composable
fun ABSWorkoutScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp)
    ) {
        // Header
        ExerciseHeader(text = "ABS")

        // Exercise List
        // You will replace these with your data model, which holds exercise information
        ExerciseCard(
            exerciseNumber = 1,
            exerciseName = "Dumbbell Crunch",
            repsInfo = "Reps 10\nRest 10sec\nRepeat 4x"
        ) {
            // Handle video link click
        }

        ExerciseCard(
            exerciseNumber = 2,
            exerciseName = "Tuck and Crunch",
            repsInfo = "Reps 15\nRest 10sec\nRepeat 4x"
        ) {
            // Handle video link click
        }

        ExerciseCard(
            exerciseNumber = 3,
            exerciseName = "Hanging Knee Raise",
            repsInfo = "Reps 15\nRest 10sec\nRepeat 4x"
        ) {
            // Handle video link click
        }

        ExerciseCard(
            exerciseNumber = 4,
            exerciseName = "Plank",
            repsInfo = "Time 1 Minute\nRest 90sec\nRepeat 2x"
        ) {
            // Handle video link click
        }
    }
}
@Preview(showBackground = true)
@Composable
fun WorkoutPlanScreenPreview() {
    ABSWorkoutScreen()
}