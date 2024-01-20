package com.dreamshape.dsfitness.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dreamshape.dsfitness.Destinations
import com.dreamshape.dsfitness.LoginViewModel
import com.dreamshape.dsfitness.R
import com.dreamshape.dsfitness.components.DSButton
import com.dreamshape.dsfitness.components.OnboardingText
import com.dreamshape.dsfitness.components.OnboardingTitle

@Composable
fun SuccessScreen(userName: String, navController: NavController, userViewModel: LoginViewModel.UserViewModel = viewModel()) {

    val successImage: Painter = painterResource(id = R.drawable.success)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = successImage,
            contentDescription = "Registration Success",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        OnboardingTitle(text = "Welcome, $userName")

        Spacer(modifier = Modifier.height(8.dp))

        OnboardingText(text = "You are all set now, let's reach your goals together with us")

        Spacer(modifier = Modifier.height(32.dp))

        DSButton(
            text = "Complete Your Profile",
            onClick = { navController.navigate(Destinations.CompleteProfileScreen) }
        )
    }
}

