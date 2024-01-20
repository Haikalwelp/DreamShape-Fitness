package com.dreamshape.dsfitness

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dreamshape.dsfitness.ui.theme.DSFitnessTheme
import com.google.android.libraries.places.api.Places

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, "AIzaSyCsNVoVdvxpIyYcQfuCeIUi45gKDCSwSss")
        }

        setContent {
            CustomMaterialTheme {

                val navController = rememberNavController()

                DSFitnessNavGraph(navController)
            }
        }
    }
}

@Composable
fun CustomMaterialTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(
            primary = Color.Blue,
            background = Color(0xFFE0E0FF)
        )
    ) {
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun DsFitnessPreview() {
    DSFitnessTheme {

    }
}