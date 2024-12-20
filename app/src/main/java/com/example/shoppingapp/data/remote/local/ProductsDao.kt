package com.example.shoppingapp.data.remote.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ProductsDao {
    @Query("SELECT * from productsentity")
    suspend fun getProducts(): List<ProductsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productsEntity: List<ProductsEntity>)
}