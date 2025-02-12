package com.example.shoppingapp.data.remote.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.data.remote.localcart.Cart
import com.example.shoppingapp.data.remote.localcart.CartDao
import com.example.shoppingapp.data.util.Converters


@Database(entities = [ProductsEntity::class, Cart::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProductsDatabase: RoomDatabase() {

    abstract val productsDao: ProductsDao
    abstract val cartDao: CartDao

}