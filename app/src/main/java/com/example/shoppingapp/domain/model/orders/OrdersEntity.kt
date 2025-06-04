package com.example.shoppingapp.domain.model.orders

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrdersEntity (
    @PrimaryKey val orderId: Int,
    val orderDate: String
)