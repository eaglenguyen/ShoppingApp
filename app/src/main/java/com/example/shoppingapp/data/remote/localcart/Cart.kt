package com.example.shoppingapp.data.remote.localcart

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cart(
    @PrimaryKey
    val id: Int,
    val itemName: String,
    val itemPrice: Int
)