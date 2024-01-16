package com.dreamshape.dsfitness.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dreamshape.dsfitness.components.DSImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteProfileScreen() {
    // Replace with your actual resource IDs

    var gender by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DSImage(
            image = painterResource(id = com.dreamshape.dsfitness.R.drawable.rgrimage1), // Make sure this is the correct resource ID
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Adjust the weight to control the image size
        )
        Text(
            text = "Let’s complete your profile",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Start)
        )
        Text(
            text = "It will help us to know more about you!",
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.Start)
        )
        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Choose Gender") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { /* Handle next focus */ })
        )
        OutlinedTextField(
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = { Text("Date of Birth") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = "Date of Birth") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { /* Handle next focus */ })
        )
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Your Weight") },
            trailingIcon = { Text("KG") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { /* Handle next focus */ })
        )
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Your Height") },
            trailingIcon = { Text("CM") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* Handle done action */ })
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /* Handle next button click */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Next", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable fun CompleteProfileScreenPreview() {
    CompleteProfileScreen()
}


