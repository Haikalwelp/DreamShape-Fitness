@file:OptIn(ExperimentalMaterial3Api::class)

package com.dreamshape.dsfitness.components

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
fun GenderPicker(
    selectedGender: String,
    onGenderSelected: (String) -> Unit,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedGender,
            onValueChange = {},
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Person Icon") },
            trailingIcon = {
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = "Dropdown Icon",
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            DropdownMenuItem(onClick = {
                onGenderSelected("Male")
                expanded = false
            }) {
                Text(text = "Male")
            }
            DropdownMenuItem(onClick = {
                onGenderSelected("Female")
                expanded = false
            }) {
                Text(text = "Female")
            }
            DropdownMenuItem(onClick = {
                onGenderSelected("Other")
                expanded = false
            }) {
                Text(text = "Other")
            }
        }
    }
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
    leadingIcon: @Composable() (() -> Unit)? = null,
    modifier: Modifier
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

@Composable
fun BottomBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        BottomBarItem(Icons.Default.Home, "Home"),
        BottomBarItem(Icons.Default.Person, "Profile"),
        BottomBarItem(Icons.Default.FitnessCenter, "Workouts"),
        BottomBarItem(Icons.Default.Map, "Map")
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3B2645))
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        BottomNavigation(
            modifier = Modifier.padding(8.dp),
            backgroundColor = Color.Transparent, // Set the background color to transparent
            contentColor = Color.White // Set the content color to white
        ) {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(item.title) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index },
                    selectedContentColor = Color.White, // Set selected content color to white
                    unselectedContentColor = Color(0x80FFFFFF) // Set unselected content color with 50% alpha
                )
            }
        }
    }
}



data class BottomBarItem(val icon: ImageVector, val title: String)

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar()
}

@Composable
fun DatePickerComponent(
    modifier: Modifier = Modifier,
    label: String = "Select Date",
    onDateSelected: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    val context = LocalContext.current
    val dialogState = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = { },
        label = { Text(label) },
        readOnly = true,
        modifier = modifier.clickable { dialogState.value = true },
        trailingIcon = {
            IconButton(
                onClick = { dialogState.value = true },
                content = {
                    Icon(Icons.Filled.CalendarToday, contentDescription = null)
                }
            )
        }
    )

    if (dialogState.value) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val listener = DatePickerDialog.OnDateSetListener { _, y, m, d ->
            calendar.set(y, m, d)
            text = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
            onDateSelected(text)
            dialogState.value = false
        }

        DatePickerDialog(
            context,
            listener,
            year,
            month,
            day
        ).show()
    }
}



@Preview(showBackground = true)
@Composable
fun DatePickerComponentPreview() {
    DatePickerComponent(onDateSelected = {})
}







