package com.example.shoppingapp.domain.model.products

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.domain.model.cart.Cart
import com.example.shoppingapp.domain.model.cart.CartDao
import com.example.shoppingapp.data.util.Converters
import com.example.shoppingapp.domain.model.junction.OrdersCartJunction
import com.example.shoppingapp.domain.model.orders.OrderDetailEntity
import com.example.shoppingapp.domain.model.orders.OrdersDao
import com.example.shoppingapp.domain.model.orders.OrdersEntity


@Database(entities = [
    ProductsEntity::class,
    Cart::class,
    OrdersEntity::class,
    OrderDetailEntity::class,
    OrdersCartJunction::class
                     ], version = 14, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProductsDatabase: RoomDatabase() {

    abstract val productsDao: ProductsDao
    abstract val cartDao: CartDao
    abstract val ordersDao: OrdersDao

}