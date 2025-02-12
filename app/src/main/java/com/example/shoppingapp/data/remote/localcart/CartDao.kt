package com.example.shoppingapp.data.remote.localcart

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cart: Cart)

    @Delete
    suspend fun delete(cart: Cart)

    @Query("DELETE FROM cart WHERE id IN (SELECT id FROM cart LIMIT 1 OFFSET :position)")
    suspend fun deleteId(position: Int)


    @Query("SELECT * from cart")
    fun getCart(): Flow<List<Cart>>


}