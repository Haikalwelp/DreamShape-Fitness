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
fun BicepsWorkoutScreen() {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp)
    ) {
        item { ExerciseHeader(text = "BICEPS") }

        val exercises = listOf(
            Triple("Standing Dumbbell Curl", "Reps 8-12\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExb3FnazgyZjBsdHd6eDYxNDB5MGNxZ2twYTU5M2kxcnJoaHhqaXBlYyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/RI3XCoEizzlqPAvDqQ/giphy-downsized-large.gif"),
            Triple("Kneeling Single Arm Curl", "Reps 8-12\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExemlpZDV5MWw2NWhwaDN0NGYxbTkwbGpxY2RhbmQwcTZhMjQxamN5byZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/1DdbZMuswm4BlBZuJC/giphy-downsized-large.gif"),
            Triple("Spider Curl", "Reps 8-12\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExMW95ZnNkZWZrNTd0dTZqamM2bG9taDFjdjE0cTRsY3UxeTJ0Ym4wdyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/OHlHwFa0cjtmUmyjEG/giphy-downsized-large.gif"),
            Triple("Concentration Curl", "Reps 8-12\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExMWs2M2htdXloZDJ2NmNkc2FkMzlxdHhzeHo1Z3U2bW1mbTc4dXF3NiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/woSB0tNA8Qtv0ha7dx/source.gif")
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
fun BicepsExercisePreview() {
    BicepsWorkoutScreen()
}
