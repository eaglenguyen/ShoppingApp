package com.example.shoppingapp.data.remote.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppingapp.domain.model.Product

@Entity
data class ProductsEntity(
    @PrimaryKey val id: Int? = null,
    val products: List<Product>
)
