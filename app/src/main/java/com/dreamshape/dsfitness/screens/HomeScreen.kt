package com.dreamshape.dsfitness.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dreamshape.dsfitness.CustomMaterialTheme
import com.dreamshape.dsfitness.HomeViewModel
import com.dreamshape.dsfitness.components.BottomBar
import com.google.firebase.auth.FirebaseAuth

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(), navController: NavHostController) {
    val userData by homeViewModel.userData.observeAsState()
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    LaunchedEffect(key1 = userId) {
        userId?.let {
            homeViewModel.fetchUserData(it)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFE0E0FF))) {
        CustomMaterialTheme {
            Scaffold(
                bottomBar = {
                    BottomBar(navController = navController)
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Welcome, ${userData?.fullName ?: "Guest"}",
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.align(Alignment.Start)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        UserDataContainer(userData)
                    }
                }
            )
        }
    }
}






@Composable
fun UserDataContainer(userData: HomeViewModel.UserData?) {
    if (userData != null) {
        CardItem("Age", "${userData.age ?: "N/A"}")
        CardItem("Height", "${userData.height ?: "N/A"} cm")
        CardItem("Weight", "${userData.weight ?: "N/A"} kg")
        BmiCard(userData.bmi)
    } else {
        Text("Loading user data...", style = MaterialTheme.typography.body1)
    }
}

@Composable
fun CardItem(label: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("$label: $value")
        }
    }
}

@Composable
fun BmiCard(bmi: Double?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 6.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("BMI: ${String.format("%.2f", bmi ?: "N/A")}", style = MaterialTheme.typography.h6)
            Text(bmiCategory(bmi), style = MaterialTheme.typography.subtitle1)
        }
    }
}

fun bmiCategory(bmi: Double?): String {
    return when {
        bmi == null -> "N/A"
        bmi < 18.5 -> "Underweight"
        bmi in 18.5..24.9 -> "Normal weight"
        bmi in 25.0..29.9 -> "Overweight"
        else -> "Obese"
    }
}
