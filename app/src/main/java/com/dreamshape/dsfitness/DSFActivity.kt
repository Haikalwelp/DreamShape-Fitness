package com.dreamshape.dsfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.dreamshape.dsfitness.ui.theme.DSFitnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DSFitnessTheme {
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