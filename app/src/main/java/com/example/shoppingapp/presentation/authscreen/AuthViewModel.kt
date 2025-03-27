package com.example.shoppingapp.presentation.authscreen

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.auth.AuthRepository
import com.example.shoppingapp.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val prefs: SharedPreferences
): ViewModel() {



    var state by mutableStateOf(AuthState())

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResult = resultChannel.receiveAsFlow()


    init {
        authenticate()
        loadUserCredentials()
    }

    private fun loadUserCredentials() {
        val email = prefs.getString("email", "No Email Found") ?: "null"
        val name = prefs.getString("name", "") ?: "null"
        state = state.copy(currentEmail = email)
        state = state.copy(currentName = name)
    }

    fun onEvent(event: AuthUiEvent) {
        when(event) {
            AuthUiEvent.SignIn -> signIn()
            is AuthUiEvent.SignInEmailChanged -> { state = state.copy(signInEmail = event.value) }
            is AuthUiEvent.SignInPasswordChanged -> { state = state.copy(signInPassword = event.value) }
            AuthUiEvent.SignUp -> signUp()
            AuthUiEvent.SignOut -> signOut()
            is AuthUiEvent.SignUpEmailChanged -> { state = state.copy(signUpEmail = event.value) }
            is AuthUiEvent.SignUpNameChanged -> { state = state.copy(signUpName = event.value) }
            is AuthUiEvent.SignUpPasswordChanged -> { state = state.copy(signUpPassword = event.value) }
        }
    }

    fun showDialogState() {
        state = state.copy(showDialog = !state.showDialog)
    }

    private fun signUp() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signUp(
                name = state.signUpName,
                email = state.signUpEmail,
                password = state.signUpPassword
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signIn(
                // sign in dont require name
                name = state.signUpName,
                email = state.signInEmail,
                password = state.signInPassword
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }



    private fun signOut() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.signOut()
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }





    private fun authenticate() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.authenticate()
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }






}