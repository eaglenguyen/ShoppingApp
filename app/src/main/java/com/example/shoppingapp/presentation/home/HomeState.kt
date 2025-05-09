package com.example.shoppingapp.presentation.home

import com.example.shoppingapp.domain.model.Product

data class HomeState (
    val productList: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val showDialog: Boolean = false,
    val selectedItemIndex: Int = 0,
    val searchQuery: String = "",
    val error: String? = null

)
