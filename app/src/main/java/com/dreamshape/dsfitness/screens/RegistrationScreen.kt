package com.dreamshape.dsfitness.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dreamshape.dsfitness.Destinations
import com.dreamshape.dsfitness.RegistrationViewModel
import com.dreamshape.dsfitness.components.DSButton
import com.dreamshape.dsfitness.components.DSInputField
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = viewModel(),
    navController: NavController
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val registrationState by viewModel.registrationState.observeAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(registrationState) {
        when (registrationState) {
            RegistrationViewModel.RegistrationState.SUCCESS -> {
                viewModel.userFirstName.value?.let { firstName ->
                    navController.navigate("success/$firstName")
                }
            }
            RegistrationViewModel.RegistrationState.EMAIL_ALREADY_REGISTERED -> {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Email already registered")
                }
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hey there,\nCreate an Account",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(32.dp))

        DSInputField(value = firstName, onValueChange = { firstName = it }, label = "First Name", leadingIcon = Icons.Default.Person)
        Spacer(Modifier.height(16.dp))

        DSInputField(value = lastName, onValueChange = { lastName = it }, label = "Last Name", leadingIcon = Icons.Default.Person)
        Spacer(Modifier.height(16.dp))

        DSInputField(value = email, onValueChange = { email = it }, label = "Email", leadingIcon = Icons.Default.Email)
        Spacer(Modifier.height(16.dp))

        DSInputField(value = password, onValueChange = { password = it }, label = "Password", leadingIcon = Icons.Default.Lock, isPassword = true)
        Spacer(Modifier.height(32.dp))

        DSButton(text = "Register", onClick = { viewModel.registerUser(firstName, lastName, email, password) })

        // ClickableText for "Have an account? Login"
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Gray)) {
                append("Have an account? ")
            }
            withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
                append("Login")
                addStringAnnotation(
                    tag = "LOGIN",
                    annotation = "Login",
                    start = length - "Login".length,
                    end = length
                )
            }
        }

        ClickableText(
            text = annotatedString,
            modifier = Modifier.padding(16.dp),
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "LOGIN", start = offset, end = offset)
                    .firstOrNull()?.let {
                        navController.navigate(Destinations.LoginScreen)
                    }
            }
        )

        SnackbarHost(hostState = snackbarHostState)
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(navController = NavController(LocalContext.current))
}
