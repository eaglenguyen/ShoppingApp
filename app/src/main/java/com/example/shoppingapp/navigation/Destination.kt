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

@Serializable
object CheckoutScreen

@Serializable
object AddressFormScreen

@Serializable
object SuccessScreen

@Serializable
object SplashScreen

@Serializable
object OrdersScreen


@Serializable
data class OrdersDetailScreen(
    val orderId: Int
)

