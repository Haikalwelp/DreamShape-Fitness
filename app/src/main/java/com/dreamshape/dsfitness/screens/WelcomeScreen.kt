package com.dreamshape.dsfitness.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dreamshape.dsfitness.R
import com.dreamshape.dsfitness.components.DSButton
import com.dreamshape.dsfitness.components.DSImage
import com.dreamshape.dsfitness.components.DSText

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
                image = painterResource(id = R.drawable.logo),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(modifier = Modifier.height(24.dp))
            DSText(
                text = "DREAM SHAPE\n\nDREAM LIFE",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 120.dp)
            )
            DSButton(
                text = "Get Started",
                onClick = onGetStartedClick,
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