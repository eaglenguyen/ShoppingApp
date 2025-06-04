package com.example.shoppingapp.domain.model.products

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ProductsDao {
    @Query("SELECT * from product")
    suspend fun getProducts(): List<ProductsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productsEntity: List<ProductsEntity>)

    @Query(
        """
            SELECT *
            FROM product
            WHERE LOWER (title) LIKE '%' || LOWER (:query) || '%'
        """
    )
    suspend fun searchProductTitle(query: String): List<ProductsEntity>




}