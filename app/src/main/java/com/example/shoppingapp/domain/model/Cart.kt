package com.example.shoppingapp.domain.model

data class OldCart (
    val id: Int,
    val userId: Int,
    val date: String,
    val product: List<CartItem>
)

data class CartItem (
    val productId: Int,
    val quantity: Int
)