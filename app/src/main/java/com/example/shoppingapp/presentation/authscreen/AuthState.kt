package com.example.shoppingapp.presentation.authscreen

data class AuthState(
    val isLoading: Boolean = false,
    val signUpName: String = "",
    val signUpEmail: String = "",
    val signUpPassword: String = "",
    val signInEmail: String = "",
    val signInPassword: String = "",
    val showDialog: Boolean = false,
    val currentName: String = "",
    val currentEmail: String = ""
    )
