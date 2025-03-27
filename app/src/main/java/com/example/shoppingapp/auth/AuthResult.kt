package com.example.shoppingapp.auth

sealed class AuthResult<T>(val data: T? = null) {
    class Authorized<T>(data: T? = null): AuthResult<T>(data)
    class Success<T>(data: T? = null) : AuthResult<T>(data)
    class Unauthorized<T>: AuthResult<T>()
    class UnknownError<T>: AuthResult<T>()
}