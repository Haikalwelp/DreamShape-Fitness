package com.dreamshape.dsfitness.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dreamshape.dsfitness.Destinations
import com.dreamshape.dsfitness.R
import com.dreamshape.dsfitness.components.OnboardingImage
import com.dreamshape.dsfitness.components.OnboardingNavButton
import com.dreamshape.dsfitness.components.OnboardingText
import com.dreamshape.dsfitness.components.OnboardingTitle

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController) {
    val onboardingScreens = listOf(
        OnboardingData(R.drawable.frame2, "Track Your Goal", "Don't worry if you have trouble determining your goals, We can help you determine your goals and track your goals"),
        OnboardingData(R.drawable.frame2, "Get Burn", "Let’s keep burning, to achieve yours goals, it hurts only temporarily, if you give up now you will be in pain forever"),
        OnboardingData(R.drawable.frame3, "Eat Well", "Let's start a healthy lifestyle with us, we can guide your diet every day. healthy eating is fun"),
        OnboardingData(R.drawable.frame4, "Achieve Success", "Embrace the burn, conquer the challenge; in the rhythm of effort, find the symphony of success")
    )

    var currentScreen by remember { mutableStateOf(0) }

    OnboardingPage(
        data = onboardingScreens[currentScreen],
        onNextClicked = {
            if (currentScreen < onboardingScreens.lastIndex) {
                currentScreen++
            } else {
                navController.navigate(Destinations.RegistrationScreen) {
                    popUpTo(Destinations.OnboardingScreen) { inclusive = true }
                }
            }
        }
    )
}

@Composable
fun OnboardingPage(data: OnboardingData, onNextClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OnboardingImage(image = painterResource(id = data.imageRes))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OnboardingTitle(text = data.title)
            OnboardingText(text = data.description)
        }
        OnboardingNavButton(onClick = onNextClicked)
    }
}


data class OnboardingData(@DrawableRes val imageRes: Int, val title: String, val description: String)
