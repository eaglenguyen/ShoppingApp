package com.example.shoppingapp.domain.model.junction

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.shoppingapp.domain.model.orders.OrderDetailEntity
import com.example.shoppingapp.domain.model.orders.OrdersEntity


data class OrderWithCartItems(
    @Embedded val order: OrdersEntity,
    @Relation(
        parentColumn = "orderId", // related to val order
        entityColumn = "id", // related to val cartItems
        associateBy = Junction(
            value = OrdersCartJunction::class,
            parentColumn = "orderId",
            entityColumn = "cartId"
        ) // joining both tables
    )
    val detailItems: List<OrderDetailEntity>
)
