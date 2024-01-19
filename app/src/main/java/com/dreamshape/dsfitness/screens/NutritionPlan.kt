package com.dreamshape.dsfitness.screens

import com.dreamshape.dsfitness.R
import com.dreamshape.dsfitness.components.MoreInformationButton
import com.dreamshape.dsfitness.components.NutritionHeader
import com.dreamshape.dsfitness.components.SectionTitle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dreamshape.dsfitness.components.FoodItem
import com.dreamshape.dsfitness.components.NutritionPlate

@Composable
fun NutritionScreen() {
    Scaffold(
        topBar = {
            NutritionHeader(title = "Nutritional Guidance", onBackClick = { /* Handle back press */ })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            SectionTitle(title = "What to eat?")
            NutritionPlate()
            FoodItem(name = "Rice", calories = 80, iconId = R.drawable.rice)
            FoodItem(name = "Tea without sugar", calories = 0, iconId = R.drawable.tea)
            FoodItem(name = "Egg", calories = 68, iconId = R.drawable.egg)
            FoodItem(name = "Salmon", calories = 250, iconId = R.drawable.fish)
            FoodItem(name = "Bread", calories = 300, iconId = R.drawable.bread)
            MoreInformationButton(onClick = { /* Handle more info click */ })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNutritionScreen() {
    NutritionScreen()
}