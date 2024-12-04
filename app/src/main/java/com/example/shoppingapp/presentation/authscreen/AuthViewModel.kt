package com.example.shoppingapp.presentation.authscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor(): ViewModel() {

    var state by mutableStateOf(AuthState())

    fun onEvent(event: AuthUiEvent) {
        when(event) {
            AuthUiEvent.SignIn -> TODO()
            is AuthUiEvent.SignInEmailChanged -> { state = state.copy(signInEmail = event.value) }
            is AuthUiEvent.SignInPasswordChanged -> { state = state.copy(signInPassword = event.value) }
            AuthUiEvent.SignUp -> TODO()
            is AuthUiEvent.SignUpEmailChanged -> { state = state.copy(signUpEmail = event.value) }
            is AuthUiEvent.SignUpNameChanged -> { state = state.copy(signUpName = event.value) }
            is AuthUiEvent.SignUpPasswordChanged -> { state = state.copy(signUpPassword = event.value) }
        }
    }


}