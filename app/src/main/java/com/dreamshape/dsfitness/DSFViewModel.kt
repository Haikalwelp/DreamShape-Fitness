package com.dreamshape.dsfitness

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


class RegistrationViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    // Live data to observe registration state in the UI
    private val _registrationState = MutableLiveData<RegistrationState>()
    val registrationState: LiveData<RegistrationState> = _registrationState

    fun registerUser(firstName: String, lastName: String, email: String, password: String) {
        // Start registration with Firebase Authentication
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // User is successfully registered and signed in
                // Now, store the additional fields in Firestore
                val userId = task.result?.user?.uid ?: return@addOnCompleteListener
                val user = hashMapOf(
                    "firstName" to firstName,
                    "lastName" to lastName,
                    "email" to email
                )
                FirebaseFirestore.getInstance().collection("users").document(userId)
                    .set(user)
                    .addOnSuccessListener {
                        _userFirstName.value = firstName // Store the first name
                        _registrationState.value = RegistrationState.SUCCESS
                    }
                    .addOnFailureListener {
                        _registrationState.value = RegistrationState.ERROR
                    }
            } else {
                // If sign in fails, check for the specific error code
                val exception = task.exception
                if (exception is FirebaseAuthUserCollisionException) {
                    // Email is already registered
                    _registrationState.value = RegistrationState.EMAIL_ALREADY_REGISTERED
                } else {
                    // Other registration errors
                    _registrationState.value = RegistrationState.ERROR
                }
            }
        }
    }

    private val _userFirstName = MutableLiveData<String>()
    val userFirstName: LiveData<String> = _userFirstName

    fun fetchUserFirstName(userId: String) {
        FirebaseFirestore.getInstance().collection("users").document(userId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                _userFirstName.value = documentSnapshot.getString("firstName")
            }
            .addOnFailureListener {
                // Handle the error case
            }
    }

    enum class RegistrationState {
        IDLE,
        SUCCESS,
        ERROR,
        EMAIL_ALREADY_REGISTERED
    }
}

class LoginViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    // Live data to observe login state in the UI
    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    fun loginUser(email: String, password: String) {
        _loginState.value = LoginState.LOADING
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // User is successfully logged in
                _loginState.value = LoginState.SUCCESS
            } else {
                // If login fails, dispatch error state based on exception
                when (task.exception) {
                    is FirebaseAuthInvalidUserException -> {
                        _loginState.value = LoginState.INVALID_USER
                    }
                    is FirebaseAuthInvalidCredentialsException -> {
                        _loginState.value = LoginState.INVALID_CREDENTIALS
                    }
                    else -> {
                        _loginState.value = LoginState.ERROR
                    }
                }
            }
        }
    }

    fun checkUserProfileComplete(onComplete: (Boolean) -> Unit) {
        val userId = auth.currentUser?.uid ?: return

        FirebaseFirestore.getInstance().collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                val isComplete = document.contains("gender") &&
                        document.contains("dateOfBirth") &&
                        document.contains("weight") &&
                        document.contains("height")
                onComplete(isComplete)
            }
            .addOnFailureListener {
                // Handle failure (e.g., assume profile is incomplete or show an error)
                onComplete(false)
            }
    }

    enum class LoginState {
        IDLE,
        LOADING,
        SUCCESS,
        INVALID_USER,
        INVALID_CREDENTIALS,
        ERROR
    }

    class UserViewModel : ViewModel() {
        private val db = FirebaseFirestore.getInstance()
        private val _firstName = MutableLiveData<String?>()
        val firstName: MutableLiveData<String?> = _firstName

        fun fetchUserFirstName(userId: String) {
            db.collection("users").document(userId).get().addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val firstName = document.getString("firstName")
                    if (firstName != null) {
                        _firstName.value = firstName
                    }
                }
            }.addOnFailureListener {
                // Handle any errors here
            }
        }
    }
}

class ProfileCompletionViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _profileCompletionState = MutableLiveData<ProfileCompletionState>()
    val profileCompletionState: LiveData<ProfileCompletionState> = _profileCompletionState

    fun completeUserProfile(gender: String, dateOfBirth: String, weight: String, height: String) {
        val userId = auth.currentUser?.uid ?: return

        val userProfile = hashMapOf(
            "gender" to gender,
            "dateOfBirth" to dateOfBirth,
            "weight" to weight,
            "height" to height
        )

        db.collection("users").document(userId)
            .update(userProfile as Map<String, Any>)
            .addOnSuccessListener {
                _profileCompletionState.value = ProfileCompletionState.SUCCESS
            }
            .addOnFailureListener {
                _profileCompletionState.value = ProfileCompletionState.ERROR
            }
    }

    enum class ProfileCompletionState {
        IDLE,
        SUCCESS,
        ERROR
    }
}

class HomeViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> = _userData

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchUserData(userId: String) {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val fullName = "${document.getString("firstName")} ${document.getString("lastName")}"
                    val dob = document.getString("dateOfBirth")
                    val weight = document.get("weight")?.toString()?.toDoubleOrNull()
                    val height = document.get("height")?.toString()?.toDoubleOrNull()

                    val age = dob?.let { calculateAge(it) }
                    val bmi = if (weight != null && height != null) calculateBMI(weight, height) else null

                    _userData.value = UserData(fullName, age, height, weight, bmi)
                }
            }
            .addOnFailureListener {
                // Handle errors
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateAge(dob: String): Int {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val birthDate = LocalDate.parse(dob, formatter)
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now()).toInt()
    }

    private fun calculateBMI(weight: Double, height: Double): Double {
        val heightInMeters = height / 100
        return weight / (heightInMeters * heightInMeters)
    }

    data class UserData(
        val fullName: String?,
        val age: Int?,
        val height: Double?,
        val weight: Double?,
        val bmi: Double?
    )
}

class ManageUserProfileViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _manageProfileState = MutableLiveData<ManageProfileState>()
    val manageProfileState: LiveData<ManageProfileState> = _manageProfileState

    fun updateProfile(weight: String, height: String) {
        _manageProfileState.value = ManageProfileState.LOADING
        val userId = auth.currentUser?.uid ?: return

        val userProfile = hashMapOf(
            "weight" to weight,
            "height" to height
        )

        db.collection("users").document(userId)
            .update(userProfile as Map<String, Any>)
            .addOnSuccessListener {
                _manageProfileState.value = ManageProfileState.SUCCESS
            }
            .addOnFailureListener {
                _manageProfileState.value = ManageProfileState.ERROR
            }
    }
    var selectedGender by mutableStateOf("Choose Gender")
    var selectedDate by mutableStateOf(TextFieldValue(""))
    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    fun fetchUserProfile() {
        val userId = auth.currentUser?.uid ?: return

        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val userData = document.data
                    // Extract user details from userData and update your LiveData or mutableStateOf variables
                    // For example:
                    val gender = userData?.get("gender") as? String ?: "Choose Gender"
                    val dateOfBirth = userData?.get("dateOfBirth") as? String ?: ""
                    val userWeight = userData?.get("weight") as? String ?: ""
                    val userHeight = userData?.get("height") as? String ?: ""

                    // Update your LiveData or mutableStateOf variables with the fetched data
                    selectedGender = gender
                    selectedDate = TextFieldValue(dateOfBirth)
                    weight = userWeight
                    height = userHeight
                }
            }
            .addOnFailureListener { exception ->
                // Handle the error
            }
    }

    enum class ManageProfileState {
        IDLE,
        LOADING,
        SUCCESS,
        ERROR
    }
}

class LogoutViewModel(private val navController: NavHostController) : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    fun logout() {
        auth.signOut()
        navController.navigate("LoginScreen") {
            popUpTo("ManageProfileScreen") { inclusive = true }
        }
    }
}



