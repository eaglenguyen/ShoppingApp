package com.example.shoppingapp.data.repository

import com.example.shoppingapp.domain.model.cart.Cart
import com.example.shoppingapp.domain.model.junction.OrderWithCartItems
import com.example.shoppingapp.domain.model.junction.OrdersCartJunction
import com.example.shoppingapp.domain.model.orders.OrderDetailEntity
import com.example.shoppingapp.domain.model.orders.OrdersDao
import com.example.shoppingapp.domain.model.orders.OrdersEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val ordersDao: OrdersDao
) {


    fun getOrdersWithCartItem(): Flow<List<OrderWithCartItems>> = ordersDao.getOrdersWithCartItems()

    // fun getOrdersWithCartItemsId(orderId: Int): Flow<OrderWithCartItems> = ordersDao.getOrderWithCartItemsById(orderId)


    // 3 Insert Room DAOs called
    suspend fun confirmOrder(order: OrdersEntity, cartItems: List<Cart>) {
        ordersDao.insertOrder(order) // Insert OrderEntity
        cartItems.forEach { cart ->
            val detail = OrderDetailEntity(
                title = cart.title,
                price = cart.price,
                quantity = cart.quantity,
                image = cart.image
            )
            val detailId = ordersDao.insertCartItemsToDetail(detail) // This returns the id of type Long, since autoGenerate = true

            ordersDao.insertOrderCartJunc(
                OrdersCartJunction(orderId = order.orderId, detailId = detailId.toInt()) // Inserts both Entities into one table
            )
        }
    }

    suspend fun clearList() = ordersDao.clearOrders()

}