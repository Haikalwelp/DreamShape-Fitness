package com.dreamshape.dsfitness

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dreamshape.dsfitness.ui.theme.DSFitnessTheme
import com.google.android.libraries.places.api.Places

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Google Places
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, "AIzaSyCsNVoVdvxpIyYcQfuCeIUi45gKDCSwSss") // Replace with your actual API key
        }

        setContent {
            MaterialTheme(colors = lightColors()) { // This applies the default light color scheme
                // Create a NavController
                val navController = rememberNavController()

                // Set up the navigation graph
                DSFitnessNavGraph(navController)
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DsFitnessPreview() {
    DSFitnessTheme {

    }
}