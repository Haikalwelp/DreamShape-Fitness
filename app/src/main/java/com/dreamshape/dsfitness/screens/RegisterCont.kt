import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dreamshape.dsfitness.components.DSImage
import com.google.android.material.datepicker.MaterialDatePicker
import com.dreamshape.dsfitness.R
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteProfileScreen() {
    var gender by remember { mutableStateOf("Male") }
    var dateOfBirth by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    val genderOptions = listOf("Male", "Female", "Helicopter")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image and texts remain unchanged

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = gender,
                onValueChange = { gender = it },
                label = { Text("Choose Gender") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                readOnly = true // Make it read-only
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                genderOptions.forEach { selection ->
                    DropdownMenuItem(
                        text = { Text(selection) },
                        onClick = {
                            gender = selection
                            expanded = false
                        }
                    )
                }
            }
        }

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



@Composable
fun ShowDatePickerDialog(
    date: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var selectedDate = Calendar.getInstance()
    try {
        selectedDate.time = formatter.parse(date) ?: Calendar.getInstance().time
    } catch (e: Exception) {
        selectedDate = Calendar.getInstance()
    }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            onDateSelected(formatter.format(calendar.time))
        }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH)
    )

    LaunchedEffect(Unit) {
        datePickerDialog.show()
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable fun CompleteProfileScreenPreview() {
    CompleteProfileScreen()
}
