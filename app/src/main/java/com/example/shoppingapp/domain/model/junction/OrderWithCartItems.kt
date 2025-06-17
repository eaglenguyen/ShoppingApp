package com.example.shoppingapp.domain.model.junction

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.shoppingapp.domain.model.orders.OrderDetailEntity
import com.example.shoppingapp.domain.model.orders.OrdersEntity


data class OrderWithCartItems(
    @Embedded val order: OrdersEntity,
    @Relation(
        parentColumn = "orderId", // id name from to OrdersEntity
        entityColumn = "id", // id name from to OrderDetailEntity
        associateBy = Junction(
            value = OrdersCartJunction::class,
            parentColumn = "orderId", // primarykey name
            entityColumn = "detailId" // primarykey name
        ) // joining both tables
    )
    val detailItems: List<OrderDetailEntity>
)
