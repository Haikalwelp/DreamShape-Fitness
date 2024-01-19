package com.dreamshape.dsfitness.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dreamshape.dsfitness.Destinations
import com.dreamshape.dsfitness.LoginViewModel
import com.dreamshape.dsfitness.components.DSButton
import com.dreamshape.dsfitness.components.DSInputField
import com.dreamshape.dsfitness.components.OnboardingText
import com.dreamshape.dsfitness.components.OnboardingTitle


@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginState by loginViewModel.loginState.observeAsState()

    // React to login state changes
    LaunchedEffect(loginState) {
        when (loginState) {
            LoginViewModel.LoginState.SUCCESS -> {
                // Check if profile is complete
                loginViewModel.checkUserProfileComplete { isComplete ->
                    if (isComplete) {
                        navController.navigate(Destinations.HomeScreen)
                    } else {
                        navController.navigate(Destinations.CompleteProfileScreen)
                    }
                }
            }
            // ... handle other states ...
            else -> {}
        }
    }

    // You might want to handle the layout in a specific way, for example:
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OnboardingTitle(text = "Welcome Back!")
        Spacer(modifier = Modifier.height(8.dp))
        OnboardingText(text = "Sign in to continue.")
        Spacer(modifier = Modifier.height(16.dp))
        DSInputField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            leadingIcon = Icons.Default.Email // Assuming you have an email icon
        )
        Spacer(modifier = Modifier.height(16.dp))
        DSInputField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            leadingIcon = Icons.Default.Lock, // Assuming you have a lock icon
            isPassword = true
        )
        Spacer(modifier = Modifier.height(32.dp))
        DSButton(
            text = "Login",
            onClick = {
                loginViewModel.loginUser(email, password)
            }
        )
        // Optionally add more UI elements like "Forgot Password?" or "Sign Up" links
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}