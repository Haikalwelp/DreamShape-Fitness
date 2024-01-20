
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dreamshape.dsfitness.components.ExerciseCard
import com.dreamshape.dsfitness.components.ExerciseHeader

@Composable
fun LegWorkoutScree() {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(top = 16.dp)
    ) {
        item {
            // Header
            ExerciseHeader(text = "LEG")
        }

        // Exercise List
        val exercises = listOf(
            Triple("Front Squat", "Reps 5\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExdGVpcXp6ZTA3d2FhNnUwa3pjdHZlMGhmMTgyZjMxMXB4eWU4dm5nYiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/d7CytCPAOG8e42FdJD/giphy-downsized-large.gif"),
            Triple("Goblet Squat", "Reps 15\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExejEwYTZ6MGR0ajF2cDVrMnNpZ2NxYjRrbWQwMHJ3eml2czZ3eG9heCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/A9eUlZ5e5lbmr1GUbE/giphy-downsized-large.gif"),
            Triple("Bulgarian Split Squat", "Reps 8-12\nRest 10sec\nRepeat 4x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExZWUydnRrZGZ5bWh6M2IyYzZteWNnMTJrbjdkZGo0cXc4aXVvNjVnaSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/gHZmL21ZACIX6a4wKW/giphy-downsized-large.gif"),
            Triple("Reverse Lunge", "Reps 10-12\nRest 10sec\nRepeat 3x", "https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExOG9qajI5Z3pxNmtkcXZ2NXg0d2xkNmplYmV5Z2c2Ymk5N3AwOTJsdiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/TTnlC8yp26YzyXe9eQ/giphy-downsized-large.gif")
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

