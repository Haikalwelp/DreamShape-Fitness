package com.dreamshape.dsfitness.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.dreamshape.dsfitness.R
import com.dreamshape.dsfitness.components.BottomBar
import com.dreamshape.dsfitness.components.FoodItem
import com.dreamshape.dsfitness.components.MoreInformationButton
import com.dreamshape.dsfitness.components.NutritionPlate
import com.dreamshape.dsfitness.components.SectionTitle

@Composable
fun NutritionScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController) // Include the BottomBar composable here
        },
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            SectionTitle(title = "What to eat?")
            NutritionPlate()
            FoodItem(name = "Rice", calories = 80, drawableId = R.drawable.rice)
            FoodItem(name = "Tea without sugar", calories = 0, drawableId = R.drawable.tea)
            FoodItem(name = "Egg", calories = 68, drawableId = R.drawable.egg)
            FoodItem(name = "Salmon", calories = 250, drawableId = R.drawable.fish)
            FoodItem(name = "Bread", calories = 300, drawableId = R.drawable.bread)
            MoreInformationButton(onClick = { /* Handle more info click */ })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNutritionScreen() {
    NutritionScreen(navController = NavHostController(LocalContext.current))
}