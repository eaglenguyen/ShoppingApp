package com.example.shoppingapp.presentation.authscreen


sealed class AuthUiEvent {
    data class SignUpNameChanged(val value: String): AuthUiEvent()
    data class SignUpEmailChanged(val value: String): AuthUiEvent()
    data class SignUpPasswordChanged(val value: String): AuthUiEvent()
    object SignUp: AuthUiEvent()

    data class SignInEmailChanged(val value: String): AuthUiEvent()
    data class SignInPasswordChanged(val value: String): AuthUiEvent()
    object SignIn: AuthUiEvent()
    object SignOut: AuthUiEvent()


}