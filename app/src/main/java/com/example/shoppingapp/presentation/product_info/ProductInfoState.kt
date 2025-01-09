package com.example.shoppingapp.presentation.product_info

import com.example.shoppingapp.domain.model.Product

data class ProductInfoState (
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null
)
