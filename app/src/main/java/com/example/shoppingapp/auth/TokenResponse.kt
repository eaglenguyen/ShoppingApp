package com.example.shoppingapp.auth

data class TokenResponse (
    val token: String,
    val name: String,
    val email: String
)