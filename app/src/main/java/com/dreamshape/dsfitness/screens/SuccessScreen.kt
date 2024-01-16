package com.dreamshape.dsfitness.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dreamshape.dsfitness.LoginViewModel
import com.dreamshape.dsfitness.R
import com.dreamshape.dsfitness.components.DSButton
import com.dreamshape.dsfitness.components.OnboardingText
import com.dreamshape.dsfitness.components.OnboardingTitle

@Composable
fun SuccessScreen(userName: String, userViewModel: LoginViewModel.UserViewModel = viewModel(), onContinueClicked: () -> Unit) {
    // Assuming the success image is stored in the drawable resource folder
    val successImage: Painter = painterResource(id = R.drawable.success)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Success image
        Image(
            painter = successImage,
            contentDescription = "Registration Success",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Welcome text
        OnboardingTitle(text = "Welcome, $userName")
        Spacer(modifier = Modifier.height(8.dp))

        // Subtitle text
        OnboardingText(text = "You are all set now, let's reach your goals together with us")

        Spacer(modifier = Modifier.height(32.dp))

        // Continue button
        DSButton(
            text = "Go To Home",
            onClick = onContinueClicked
        )
    }
}

