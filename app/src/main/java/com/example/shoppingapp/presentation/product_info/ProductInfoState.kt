package com.example.shoppingapp.presentation.product_info

import com.example.shoppingapp.data.remote.localcart.Cart
import com.example.shoppingapp.domain.model.Product

data class ProductInfoState (
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null,
    val cartList: List<Cart> = emptyList(),
    val totalPrice: Double? = 0.0,
    val totalQuantity: Int? = 0,

    val fullName: String = "",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val zipcode: String = "",
    val displayAddress: String = ""

)
