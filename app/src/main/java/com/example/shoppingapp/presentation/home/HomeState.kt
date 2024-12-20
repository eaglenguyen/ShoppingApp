package com.example.shoppingapp.presentation.home

import com.example.shoppingapp.domain.model.Product

data class HomeState (
    val productList: List<Product> = emptyList(),
    val isLoading: Boolean = false
)
