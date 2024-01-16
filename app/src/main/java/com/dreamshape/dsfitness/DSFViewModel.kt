package com.dreamshape.dsfitness

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore

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
