package com.example.shoppingapp.domain.model.junction

import androidx.room.Entity
import androidx.room.Index

// table that connects between OrderEntity and OrderDetailEntity ( their primary keys )
@Entity(
    primaryKeys = ["orderId", "detailId"], // two primary keys, ensures that each orderId/cartId combo are unique
    tableName = "order_and_cart_junc",
)
data class OrdersCartJunction (
    val orderId: Int,
    val detailId: Int
)