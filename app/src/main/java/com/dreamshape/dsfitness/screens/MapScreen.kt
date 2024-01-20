package com.dreamshape.dsfitness.screens


import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.dreamshape.dsfitness.components.MapComponent


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MapScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Nearby Gyms") })
        }
    ) {
        MapComponent()
    }
}