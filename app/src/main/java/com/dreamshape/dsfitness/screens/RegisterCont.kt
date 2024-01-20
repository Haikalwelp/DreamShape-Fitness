
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.dreamshape.dsfitness.Destinations
import com.dreamshape.dsfitness.ProfileCompletionViewModel
import com.dreamshape.dsfitness.R
import com.dreamshape.dsfitness.components.DSButton
import com.dreamshape.dsfitness.components.DatePickerComponent
import com.dreamshape.dsfitness.components.GenderPicker
import com.dreamshape.dsfitness.components.ImageComponent
import com.dreamshape.dsfitness.components.InputFieldComponent


@Composable
fun CompleteProfileScreen(navController: NavController, profileCompletionViewModel: ProfileCompletionViewModel = viewModel()) {
    var selectedGender by remember { mutableStateOf("Choose Gender") }
    var selectedDate by remember { mutableStateOf(TextFieldValue("")) }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    val inputFieldWidthModifier = Modifier.fillMaxWidth()

    val profileCompletionState by profileCompletionViewModel.profileCompletionState.observeAsState()


    LaunchedEffect(profileCompletionState) {
        when (profileCompletionState) {
            ProfileCompletionViewModel.ProfileCompletionState.SUCCESS -> {
                navController.navigate(Destinations.HomeScreen)
            }
            ProfileCompletionViewModel.ProfileCompletionState.ERROR -> {

            }
            else -> {}
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))


        ImageComponent(drawableId = R.drawable.rgrimage1)

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            "Let's complete your profile",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        Text(
            "It will help us to know more about you!",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))
        GenderPicker(
            selectedGender = selectedGender,
            onGenderSelected = { newGender ->
                selectedGender = newGender
            },
            modifier = inputFieldWidthModifier
        )

        Spacer(modifier = Modifier.height(16.dp))
        DatePickerComponent(
            label = "Select Date",
            onDateSelected = { date ->
                selectedDate = TextFieldValue(date)
            },
            modifier = inputFieldWidthModifier
        )

        Spacer(modifier = Modifier.height(16.dp))
        InputFieldComponent(
            value = weight,
            onValueChange = { weight = it },
            label = "Your Weight",
            leadingIcon = { Icon(Icons.Filled.FitnessCenter, contentDescription = "Weight Icon") },
            modifier = inputFieldWidthModifier
        )

        Spacer(modifier = Modifier.height(16.dp))
        InputFieldComponent(
            value = height,
            onValueChange = { height = it },
            label = "Your Height",
            leadingIcon = { Icon(Icons.Filled.Height, contentDescription = "Height Icon") },
            modifier = inputFieldWidthModifier
        )

        Spacer(modifier = Modifier.height(24.dp))
        DSButton(
            text = "Next",
            modifier = inputFieldWidthModifier,
            onClick = {
                profileCompletionViewModel.completeUserProfile(
                    gender = selectedGender,
                    dateOfBirth = selectedDate.text,
                    weight = weight,
                    height = height
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CompleteProfileScreenPreview() {
    CompleteProfileScreen(navController = NavController(LocalContext.current))
}