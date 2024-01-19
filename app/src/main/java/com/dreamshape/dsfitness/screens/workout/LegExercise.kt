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
fun LegWorkoutScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp)
    ) {
        // Header
        ExerciseHeader(text = "LEG")

        // Exercise List
        // You will replace these with your data model, which holds exercise information
        ExerciseCard(
            exerciseNumber = 1,
            exerciseName = "Front Squat",
            repsInfo = "Reps 5\nRest 10sec\nRepeat 4x"
        ) {
            // Handle video link click
        }

        ExerciseCard(
            exerciseNumber = 2,
            exerciseName = "Goblet Squat",
            repsInfo = "Reps 15\nRest 10sec\nRepeat 4x"
        ) {
            // Handle video link click
        }

        ExerciseCard(
            exerciseNumber = 3,
            exerciseName = "Bulgarian Split Squat",
            repsInfo = "Reps 8-12\nRest 10sec\nRepeat 4x"
        ) {
            // Handle video link click
        }

        ExerciseCard(
            exerciseNumber = 4,
            exerciseName = "Reverse Lunge",
            repsInfo = "Reps 10-12\nRest 10sec\nRepeat 3x"
        ) {
            // Handle video link click
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LegExercisePreview() {
    LegWorkoutScreen()
}