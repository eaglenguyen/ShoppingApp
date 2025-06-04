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

    fun getOrdersWithCartItemsId(orderId: Int): Flow<OrderWithCartItems> = ordersDao.getOrderWithCartItemsById(orderId)


    // Both Insert Room called
    suspend fun confirmOrder(order: OrdersEntity, cartItems: List<Cart>) {
        ordersDao.insertOrder(order)
        cartItems.forEach { cart ->
            val detail = OrderDetailEntity(
                title = cart.title,
                price = cart.price,
                quantity = cart.quantity,
                image = cart.image
            )
            val detailId = ordersDao.insertCartItemsToDetail(detail)

            ordersDao.insertOrderCartJunc(
                OrdersCartJunction(orderId = order.orderId, cartId = detailId.toInt())
            )
        }
    }

    suspend fun clearList() = ordersDao.clearOrders()

}