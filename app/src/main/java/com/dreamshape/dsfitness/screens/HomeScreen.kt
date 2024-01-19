package com.dreamshape.dsfitness.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dreamshape.dsfitness.HomeViewModel
import com.google.firebase.auth.FirebaseAuth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    val userData by homeViewModel.userData.observeAsState()
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    LaunchedEffect(key1 = userId) {
        userId?.let {
            homeViewModel.fetchUserData(it)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Welcome, ${userData?.fullName ?: "Guest"}",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        UserDataContainer(userData)
    }
}

@Composable
fun UserDataContainer(userData: HomeViewModel.UserData?) {
    userData?.let {
        Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Age: ${it.age ?: "N/A"}")
                Text("Height: ${it.height ?: "N/A"} cm")
                Text("Weight: ${it.weight ?: "N/A"} kg")
                Text("BMI: ${String.format("%.2f", it.bmi ?: "N/A")}")
            }
        }
    } ?: Text("Loading user data...", style = MaterialTheme.typography.body1)
}