package com.example.shoppingapp.presentation.product_info

import com.example.shoppingapp.domain.model.cart.Cart
import com.example.shoppingapp.domain.model.Product
import com.example.shoppingapp.domain.model.junction.OrderWithCartItems
import com.example.shoppingapp.domain.model.orders.OrdersEntity

data class SharedState (
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null,
    val cartList: List<Cart> = emptyList(),
    val totalPrice: Double? = 0.0,
    val totalQuantity: Int? = 0,


    // Address form
    val fullName: String = "John Doe",
    val address: String = "325 15th Eight Ave",
    val city: String = "New York",
    val state: String = "NY",
    val zipcode: String = "12345",
    val displayAddress: String = "",

    // Order History
    val orderList: List<OrderWithCartItems> = emptyList(),

    val orderListId: OrderWithCartItems = OrderWithCartItems(
        order = OrdersEntity(orderId = 0, orderDate = ""),
        detailItems = emptyList()
    )

)
