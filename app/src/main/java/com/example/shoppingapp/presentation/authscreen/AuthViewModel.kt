package com.example.shoppingapp.presentation.authscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor(): ViewModel() {

    var state by mutableStateOf(AuthState())

    fun onEvent(event: AuthUiEvent) {

    }


}