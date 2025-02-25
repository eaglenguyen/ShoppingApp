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

    fun getTotalPrice(): Flow<Double?> = cartDao.getTotalPrice()

    suspend fun addToCart(product: Product) {
        val existingItem = cartDao.getCartItemById(product.id)

        if (existingItem != null) {
            cartDao.increaseQuantity(product.id)
        } else {
            val cartItem = Cart(
                id = product.id,
                title = product.title,
                price = product.price,
            )
            cartDao.insert(cartItem)
        }
    }
    suspend fun removeFromCart(product: Product) {
        val cartItem = Cart(
            id = product.id,
            title = product.title,
            price = product.price,
        )
        cartDao.delete(cartItem)
    }

    suspend fun removeViaId(position: Int) {
        cartDao.deleteId(position)
    }

    suspend fun increaseQuantityCart(position: Int) {
        cartDao.increaseQuantityCart(position)
    }

    suspend fun removeQuantityCart(position: Int) {
        cartDao.removeQuantityCart(position)
    }

    suspend fun removeIfZero() {
        cartDao.deleteZeroQuantityItems()
    }


}