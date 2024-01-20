package com.dreamshape.dsfitness.screens.workout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dreamshape.dsfitness.components.ExerciseCard
import com.dreamshape.dsfitness.components.ExerciseHeader

@Composable
fun ChestWorkoutScreen() {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp)
    ) {
        item { ExerciseHeader(text = "CHEST") }

        val exercises = listOf(
            Triple("Incline Push Up", "Reps 10\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExb29haXdidG01M3RtMWRmZ3kzbjY2bG41bzhvbTdpb3R0d3R5bmU0NyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/rL0FI7Znxm9TSY9hIN/giphy-downsized-large.gif"),
            Triple("Flat Bench Press", "Reps 8-12\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/GOMLO8jF0WkjtmNnjT/giphy-downsized-large.gif"),
            Triple("Incline Bench Press", "Reps 8-12\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/IRjVGrFjSxPJW0rGM8/giphy-downsized-large.gif"),
            Triple("Push up", "Reps 10-12\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExcmRvczVqOTNqMmhvMGM4eDg1d2s0YzE2d3R6aG5sNmc1eXhkYWJybSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/Wa6mfGPe2pjEoYbSla/giphy-downsized-large.gif")
        )

        items(exercises) { (name, repsInfo, gifUrl) ->
            ExerciseCard(
                exerciseNumber = exercises.indexOf(Triple(name, repsInfo, gifUrl)) + 1,
                exerciseName = name,
                repsInfo = repsInfo,
                gifUrl = gifUrl
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChestExercisePreview() {
    ChestWorkoutScreen()
}
