@file:OptIn(ExperimentalMaterial3Api::class)

package com.dreamshape.dsfitness.components

import android.app.DatePickerDialog
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.dreamshape.dsfitness.R
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
fun BottomBar(navController: NavHostController){
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        BottomBarItem(Icons.Default.Home, "Home"),
        BottomBarItem(Icons.Default.Person, "Profile"),
        BottomBarItem(Icons.Default.FitnessCenter, "Workouts"),
        BottomBarItem(Icons.Default.Restaurant, "Nutrition"),
        BottomBarItem(Icons.Default.Map, "Map")
    )

    BottomNavigation(
        backgroundColor = Color(0xFF0D2136),
        contentColor = Color.White,
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                                tint = Color.White
                    )
                },
                label = { Text(item.title, fontSize = 9.sp, color = Color.White) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    when (index) {
                        0 -> navController.navigate("home") // Navigate to HomeScreen
                        1 -> navController.navigate("manageProfile")
                        2 -> navController.navigate("workoutPlan")// Navigate to ManageProfileScreen
                        3 -> navController.navigate("nutritionPlan")
                        4 -> navController.navigate("map")
                    }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color(0x80FFFFFF),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}




data class BottomBarItem(val icon: ImageVector, val title: String)

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(navController = NavHostController(LocalContext.current))
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

@Composable
fun TopBar(title: String, onBackClick: () -> Unit, onActionClick: () -> Unit) {
    TopAppBar(
        title = { androidx.compose.material.Text(text = title, color = Color.White) },
        navigationIcon = {
            androidx.compose.material.IconButton(onClick = onBackClick) {
                androidx.compose.material.Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        },
        actions = {
            androidx.compose.material.IconButton(onClick = { /* TODO: Share action */ }) {
                androidx.compose.material.Icon(Icons.Filled.Share, contentDescription = "Share", tint = Color.White)
            }
            androidx.compose.material.IconButton(onClick = onActionClick) {
                androidx.compose.material.Icon(Icons.Filled.MoreVert, contentDescription = "More", tint = Color.White)
            }
        },
        backgroundColor = Color.Black,
        contentColor = Color.White
    )
}

@Composable
fun WorkoutOptionCard(
    drawableId: Int,
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit // Adding a click listener
) {
    // Wrap Card with Modifier.clickable to make it clickable
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() } // Adding clickable modifier
    ) {
        Column {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF000000))
            ) {
                androidx.compose.material.Text(
                    text = title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp)
                )
            }
        }
    }
}


@Composable
fun NutritionHeader(title: String, onBackClick: () -> Unit) {
    TopAppBar(
        title = { androidx.compose.material.Text(text = title, color = Color.White) },
        navigationIcon = {
            androidx.compose.material.IconButton(onClick = { onBackClick() }) {
                androidx.compose.material.Icon(Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White)
            }
        },
        backgroundColor = Color(0xFF0C0C0C),
        contentColor = Color.White,
        elevation = 12.dp
    )
}

@Composable
fun NutritionPlate() {
    // This would be a custom drawing or image representing the nutrition plate
    Image(
        painter = painterResource(id = R.drawable.nutrition_pic),
        contentDescription = "Healthy Eating Plate",
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}

@Composable
fun FoodItem(name: String, calories: Int, drawableId:Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = null, // decorative
            modifier = Modifier.size(40.dp).weight(1f),
            contentScale = ContentScale.Fit, // You can adjust contentScale as needed
        )
        androidx.compose.material.Text(
            text = name,
            modifier = Modifier.weight(5f),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        androidx.compose.material.Text(
            text = "$calories kcal",
            modifier = Modifier.weight(2f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            color = Color.Gray
        )
    }
    Divider()
}
@Composable
fun SectionTitle(title: String, padding: PaddingValues = PaddingValues(all = 16.dp)) {
    androidx.compose.material.Text(
        text = title,
        style = TextStyle(
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(padding)
    )
}

@Composable
fun MoreInformationButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        shape = CircleShape
    ) {
        androidx.compose.material.Text("More information", modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun ExerciseHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    androidx.compose.material.Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ExerciseCard(
    exerciseNumber: Int,
    exerciseName: String,
    repsInfo: String,
    gifUrl: String
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Using GifImage to display the GIF
            GifImage(
                gifUrl = gifUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f) // Adjust aspect ratio as needed
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "$exerciseNumber. $exerciseName",
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = repsInfo,
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DatePickerComponentPreview() {
    DatePickerComponent(onDateSelected = {})
}


@Composable
fun GifImage(
    gifUrl: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = gifUrl).apply {
                size(Size.ORIGINAL)
            }.build(),
            imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
    )
}

@Composable
fun RedButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        modifier = Modifier
            .padding(8.dp)
            .height(48.dp) // Adjust the height as needed
    ) {
        Text(text = text, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun RedButtonPreview() {
    RedButton(text = "Log Out", onClick = {})
}


