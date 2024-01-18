@file:OptIn(ExperimentalMaterial3Api::class)

package com.dreamshape.dsfitness.components

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.material.datepicker.MaterialDatePicker

@Composable
fun DSImage(modifier: Modifier = Modifier, image: Painter) {
    Image(
        painter = image,
        contentDescription = "DS Fitness Logo",
        modifier = modifier
            .aspectRatio(1f) // Keep the image aspect ratio 1:1
    )
}


@Composable
fun DSText(modifier: Modifier = Modifier, text: String) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = TextAlign.Center, // Center the text
        fontSize = 38.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Gray
    )
}

@Composable
fun DSButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth() then Modifier.height(48.dp), // Adjust the height as needed
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D1617))
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}



//Onboarding Components

@Composable
fun OnboardingImage(image: Painter) {
    Image(
        painter = image,
        contentDescription = "Onboarding Image",
        modifier = Modifier
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun OnboardingTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    )
}

@Composable
fun OnboardingText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}

@Composable
fun OnboardingNavButton(onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.onSurface)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Next",
            tint = MaterialTheme.colorScheme.surface
        )
    }
}

//Registration Components

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun DSInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = leadingIcon?.let {
            { Icon(imageVector = it, contentDescription = null) }
        },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun DSButton() {
        DSButton(text = "Get Started", onClick = {})
}

@Composable
fun DatePickerComponent(selectedDate: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val datePickerDialog = MaterialDatePicker.Builder.datePicker().build()

    OutlinedTextField(
        value = if (selectedDate.isNotEmpty()) selectedDate else "Select Date",
        onValueChange = { /* Do nothing as the date is picked from the dialog */ },
        leadingIcon = {
            Icon(Icons.Filled.CalendarToday, contentDescription = "Calendar Icon")
        },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                datePickerDialog.show((context as AppCompatActivity).supportFragmentManager, "DATE_PICKER")
            }
    )

    datePickerDialog.addOnPositiveButtonClickListener {
        // Use SimpleDateFormat or any other formatting as needed
        onDateSelected(datePickerDialog.headerText)
    }
}


@Preview(showBackground = true)
@Composable
fun DatePickerComponentPreview() {
    DatePickerComponent(selectedDate = "") {}
}

@Composable
fun GenderSelectorComponent(selectedGender: String, onGenderSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val genders = listOf("Male", "Female", "Other")

    OutlinedTextField(
        value = selectedGender,
        onValueChange = { /* Do nothing as the gender is picked from the dropdown */ },
        leadingIcon = {
            Icon(Icons.Filled.Person, contentDescription = "Person Icon")
        },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.fillMaxWidth()
    ) {
        genders.forEach { gender ->
            DropdownMenuItem(onClick = {
                onGenderSelected(gender)
                expanded = false
            }) {
                Text(text = gender)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GenderSelectorComponentPreview() {
    GenderSelectorComponent(selectedGender = "") {}
}

@Composable
fun ImageComponent(drawableId: Int) {
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = "Profile Image",
        modifier = Modifier.size(128.dp)
    )
}

@Composable
fun InputFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = leadingIcon,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}




