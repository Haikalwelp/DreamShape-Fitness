package com.dreamshape.dsfitness.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dreamshape.dsfitness.components.DSInputField
import com.dreamshape.dsfitness.components.DSButton
import com.dreamshape.dsfitness.components.OnboardingText
import com.dreamshape.dsfitness.components.OnboardingTitle


@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                // Handle the login logic here
            }
        )
        // Optionally add more UI elements like "Forgot Password?" or "Sign Up" links
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}