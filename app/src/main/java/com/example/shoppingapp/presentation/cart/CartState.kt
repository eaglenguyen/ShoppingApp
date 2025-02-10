package com.example.shoppingapp.presentation.cart

import com.example.shoppingapp.domain.model.OldCart
import com.example.shoppingapp.domain.model.Product

data class CartState(
    val cartList: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val cart: OldCart? = null
)
