package com.example.shoppingapp.domain.model.products

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppingapp.domain.model.Rates

@Entity (tableName = "product")
data class ProductsEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: String,
    val category: String,
    val description: String,
    val image: String,
    val rating: Rates
)
