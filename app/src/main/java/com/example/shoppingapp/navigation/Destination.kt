package com.example.shoppingapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object SignUpScreen

@Serializable
object SignInScreen

@Serializable
object HomeScreen

@Serializable
object ProfileScreen

@Serializable
object SettingsScreen

@Serializable
data class DetailScreen(
    val id: Int
)

@Serializable
object CartScreen

sealed class Cart {


}