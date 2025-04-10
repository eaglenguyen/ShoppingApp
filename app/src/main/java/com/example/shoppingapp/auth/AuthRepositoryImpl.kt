package com.example.shoppingapp.auth

import android.content.SharedPreferences
import android.util.Log
import retrofit2.HttpException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences
): AuthRepository {

    override suspend fun signUp(name: String?, email: String, password: String): AuthResult<Unit> {
        return try {
            api.signUp(
                request = AuthRequest(
                    name = name,
                    email = email,
                    password = password
                )
            )
            signIn(name,email,password)
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
            }
        }


    override suspend fun signIn(name: String?, email: String, password: String): AuthResult<Unit> {
        return try {
            val response = api.signIn(
                request = AuthRequest(
                    name = name,
                    email = email,
                    password = password
                )
            )
            prefs.edit()
                .putString("jwt", response.token)
                .putString("name", response.name)
                .putString("email", response.email)
                .apply()

            AuthResult.Authorized()

        } catch (e: HttpException) {
            Log.e("AuthRepository", "signIn error: ${e.message()}", e)
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                Log.e("AuthRepository", "Unexpected Exception: ${e.message}", e)
                AuthResult.UnknownError()
            }
        }
    }




    override suspend fun signOut(): AuthResult<Unit> {
        return try {
            prefs.edit().remove("jwt").apply()
            AuthResult.Unauthorized()
        } catch (e: HttpException){
            AuthResult.UnknownError()
        }
    }


    override suspend fun authenticate(): AuthResult<Unit> {
        return try {
            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
            api.authenticate("Bearer $token")
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        }
    }
}