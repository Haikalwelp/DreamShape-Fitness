package com.dreamshape.dsfitness.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dreamshape.dsfitness.components.DSButton
import com.dreamshape.dsfitness.components.DSImage
import com.dreamshape.dsfitness.components.DSText
import com.dreamshape.dsfitness.R

@Composable
fun WelcomeScreen(onGetStartedClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DSImage(
                image = painterResource(id = R.drawable.logo), // Make sure this is the correct resource ID
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Adjust the weight to control the image size
            )
            Spacer(modifier = Modifier.height(24.dp))
            DSText(
                text = "DREAM SHAPE\n\nDREAM LIFE",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally) // Align text to the center horizontally
                    .padding(bottom = 120.dp)
            )
            DSButton(
                text = "Get Started",
                onClick = onGetStartedClick, // This will now trigger the navigation
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 32.dp)
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    MaterialTheme {
        WelcomeScreen(onGetStartedClick = {})
    }
}