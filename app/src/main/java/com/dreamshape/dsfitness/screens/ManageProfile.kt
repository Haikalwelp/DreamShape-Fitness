package com.dreamshape.dsfitness.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.dreamshape.dsfitness.LogoutViewModel
import com.dreamshape.dsfitness.ManageUserProfileViewModel
import com.dreamshape.dsfitness.R
import com.dreamshape.dsfitness.components.BottomBar
import com.dreamshape.dsfitness.components.DSButton
import com.dreamshape.dsfitness.components.ImageComponent
import com.dreamshape.dsfitness.components.InputFieldComponent
import com.dreamshape.dsfitness.components.RedButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageProfileScreen(navController: NavHostController) {
    val logoutViewModel: LogoutViewModel = viewModel(
        factory = LogoutViewModelFactory(navController)
    )
    val context = LocalContext.current
    val manageProfileViewModel: ManageUserProfileViewModel = viewModel()
    val manageProfileState by manageProfileViewModel.manageProfileState.observeAsState()
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(manageProfileState) {
        when (manageProfileState) {
            ManageUserProfileViewModel.ManageProfileState.SUCCESS -> {
                Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show()
                isLoading = false
            }
            ManageUserProfileViewModel.ManageProfileState.LOADING -> {
                isLoading = true
            }
            else -> {
                isLoading = false
            }
        }
    }

    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    val inputFieldWidthModifier = Modifier.fillMaxWidth()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Profile") },
                actions = {
                    RedButton(text = "Logout") {
                        logoutViewModel.logout()
                    }
                },
            )
        },
        bottomBar = {
            BottomBar(navController = navController) // Include the BottomBar composable here
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Use padding from Scaffold
                .padding(horizontal = 16.dp) // Additional horizontal padding
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            ImageComponent(drawableId = R.drawable.rgrimage1)

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                "Manage Your Profile",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
            Text(
                "Update your profile information",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            InputFieldComponent(
                value = weight,
                onValueChange = { weight = it },
                label = "Your Weight",
                leadingIcon = { Icon(Icons.Filled.FitnessCenter, contentDescription = "Weight Icon") },
                modifier = inputFieldWidthModifier
            )

            Spacer(modifier = Modifier.height(16.dp))
            InputFieldComponent(
                value = height,
                onValueChange = { height = it },
                label = "Your Height",
                leadingIcon = { Icon(Icons.Filled.Height, contentDescription = "Height Icon") },
                modifier = inputFieldWidthModifier
            )

            Spacer(modifier = Modifier.height(24.dp))
            DSButton(
                text = "Update",
                modifier = inputFieldWidthModifier,
                onClick = {
                    manageProfileViewModel.updateProfile(
                        weight = weight,
                        height = height
                    )
                }
            )
        }
    }
}

class LogoutViewModelFactory(
    private val navController: NavHostController
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogoutViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LogoutViewModel(navController) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Preview(showBackground = true)
@Composable
fun ManageProfileScreenPreview() {
    ManageProfileScreen(navController = NavHostController(LocalContext.current))
}
