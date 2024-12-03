package com.example.shoppingapp.auth

data class AuthRequest (
    val name: String?,
    val email: String,
    val password: String
)