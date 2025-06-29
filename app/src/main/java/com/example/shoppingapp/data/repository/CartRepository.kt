package com.example.shoppingapp.data.repository

import com.example.shoppingapp.domain.model.cart.Cart
import com.example.shoppingapp.domain.model.cart.CartDao
import com.example.shoppingapp.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {

    fun getCart(): Flow<List<Cart>> = cartDao.getCart()

    fun getTotalPrice(): Flow<Double?> = cartDao.getTotalPrice()

    fun getTotalQuantity(): Flow<Int?> = cartDao.getTotalQuantity()

    suspend fun clearCart() = cartDao.clearCart()

    suspend fun addToCart(product: Product) {
        val existingItem = cartDao.getCartItemById(product.id)

        if (existingItem != null) {
            cartDao.increaseQuantity(product.id, product.price.toDouble())
        } else {
            val cartItem = Cart(
                id = product.id,
                title = product.title,
                price = product.price,
                image = product.image
            )
            cartDao.insert(cartItem)
        }
    }
    suspend fun deleteCartItem(product: Product) {
        val cartItem = Cart(
            id = product.id,
            title = product.title,
            price = product.price,
            image = product.image
        )
        cartDao.delete(cartItem)
    }

    suspend fun removeViaId(position: Int) {
        cartDao.deleteId(position)
    }

    suspend fun increaseQuantityCart(position: Int, itemPrice: Double) {
        cartDao.increaseQuantityCart(position, itemPrice)
    }

    suspend fun removeQuantityCart(position: Int, itemPrice: Double) {
        cartDao.removeQuantityCart(position, itemPrice)
    }

    suspend fun removeIfZero(position: Int) {
        cartDao.deleteZeroQuantityItems(position)
    }


}