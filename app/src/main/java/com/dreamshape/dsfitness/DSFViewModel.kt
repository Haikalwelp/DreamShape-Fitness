package com.dreamshape.dsfitness

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
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

    enum class RegistrationState {
        IDLE,
        SUCCESS,
        ERROR,
        EMAIL_ALREADY_REGISTERED
    }
}
