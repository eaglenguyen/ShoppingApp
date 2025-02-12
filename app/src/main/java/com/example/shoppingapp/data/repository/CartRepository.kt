package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.remote.localcart.Cart
import com.example.shoppingapp.data.remote.localcart.CartDao
import com.example.shoppingapp.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {

    fun getCart(): Flow<List<Cart>> = cartDao.getCart()

    suspend fun addToCart(product: Product) {
        val cartItem = Cart(
            id = product.id,
            title = product.title,
            price = product.price
        )
        cartDao.insert(cartItem)
    }

    suspend fun removeFromCart(product: Product) {
        val cartItem = Cart(
            id = product.id,
            title = product.title,
            price = product.price
        )
        cartDao.delete(cartItem)
    }

    suspend fun removeViaId(position: Int) {
        cartDao.deleteId(position)
    }

}