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
fun ABSWorkoutScreen() {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp)
    ) {
        item { ExerciseHeader(text = "Abs Workout") }

        val exercises = listOf(
            Triple("Dumbbell Crunch", "Reps 10\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExYjVqZWZiZWc2bXRvbjh5NDZ3bHZxeW9kcHh2Z3hjdWRvbjNraXIxbyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/lS4LUokMSsbMll1XQQ/giphy-downsized-large.gif"),
            Triple("Tuck and Crunch", "Reps 15\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExaTE5bzBmb2s4NjRxMGtmdjN6ZThjdml3b3l2cHppajQ1c2h4dXVubyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/GVvOkcJkxcH0Oe2YPQ/giphy-downsized-large.gif"),
            Triple("Hanging Knee Raise", "Reps 15\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExdW9sb2RnbzZ0bGRuMHh3N2dvc2xtNHI3YTA2bjI5MW5zeTBvcmU4ayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/P6S2aA8PKZYRsPUzuM/giphy-downsized-large.gif"),
            Triple("Plank", "Time 1 Minute\nRest 90sec\nRepeat 2x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExZ3pvZW14YW1vZjFnMGNqa251OTQ0dHBnZDgzdmJhZjUzZXJxeTlyZyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/EjO12cD5NXQB5vgsYh/giphy-downsized-large.gif")
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
fun ABSExercisePreview() {
    ABSWorkoutScreen()
}

@Preview(showBackground = true)
@Composable
fun WorkoutPlanScreenPreview() {
    ABSWorkoutScreen()
}