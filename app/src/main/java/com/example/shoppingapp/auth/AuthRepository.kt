package com.example.shoppingapp.auth

interface AuthRepository {
    suspend fun signUp(name: String?, email: String, password: String): AuthResult<Unit>
    suspend fun signIn(name: String?, email: String, password: String): AuthResult<Unit>
    suspend fun signOut(): AuthResult<Unit>
    suspend fun authenticate(): AuthResult<Unit>
}