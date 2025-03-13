package com.example.shoppingapp.data.remote.localcart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey val id: Int,
    val title: String,
    val price: String,
    val quantity: Int = 1,
    val image: String
)