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
fun BicepsWorkoutScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp)
    ) {
        // Header
        ExerciseHeader(text = "BICEPS")

        // Exercise List
        // You will replace these with your data model, which holds exercise information
        ExerciseCard(
            exerciseNumber = 1,
            exerciseName = "Standing Dumbbell Curl",
            repsInfo = "Reps 8-12\nRest 10sec\nRepeat 4x"
        ) {
            // Handle video link click
        }

        ExerciseCard(
            exerciseNumber = 2,
            exerciseName = "Kneeling Single Arm Curl",
            repsInfo = "Reps 8-12\nRest 10sec\nRepeat 4x"
        ) {
            // Handle video link click
        }

        ExerciseCard(
            exerciseNumber = 3,
            exerciseName = "Spider Curl",
            repsInfo = "Reps 8-12\nRest 10sec\nRepeat 4x"
        ) {
            // Handle video link click
        }

        ExerciseCard(
            exerciseNumber = 4,
            exerciseName = "Concentration Curl",
            repsInfo = "Reps 8-12\nRest 10sec\nRepeat 4x"
        ) {
            // Handle video link click
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BicepsExercisePreview() {
    BicepsWorkoutScreen()
}