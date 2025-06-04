package com.example.shoppingapp.domain.model.orders

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_details")
data class OrderDetailEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val price: String,
    val quantity: Int = 1,
    val image: String
)
