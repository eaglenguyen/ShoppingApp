package com.example.shoppingapp.data.remote.localcart

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shoppingapp.data.util.Converters

@Database(entities = [Cart::class], version = 1, exportSchema = false)

abstract class CartDatabase: RoomDatabase() {

    abstract val cartDao: CartDao

}