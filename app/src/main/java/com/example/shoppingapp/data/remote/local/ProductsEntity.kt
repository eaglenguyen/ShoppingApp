package com.example.shoppingapp.data.remote.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppingapp.domain.model.Rates

@Entity
data class ProductsEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: String,
    val category: String,
    val description: String,
    val image: String,
    val rating: Rates
)
