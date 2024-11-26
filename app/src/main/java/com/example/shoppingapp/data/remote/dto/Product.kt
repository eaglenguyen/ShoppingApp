package com.example.shoppingapp.data.remote.dto

data class Product(
    val id: Int,
    val title: String,
    val price: String,
    val category: String,
    val description: String,
    val image: String
)