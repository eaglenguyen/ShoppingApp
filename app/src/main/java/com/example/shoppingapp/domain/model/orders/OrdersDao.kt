package com.example.shoppingapp.domain.model.orders

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.shoppingapp.domain.model.junction.OrderWithCartItems
import com.example.shoppingapp.domain.model.junction.OrdersCartJunction
import kotlinx.coroutines.flow.Flow


@Dao
interface OrdersDao {

    // Both Insert operations need to work together
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrdersEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderCartJunc(junc: OrdersCartJunction)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItemsToDetail(detailItem: OrderDetailEntity): Long


    @Transaction // ensure that this method should happen atomically , either all succeed or all fails
    @Query("SELECT * FROM orders")
    fun getOrdersWithCartItems(): Flow<List<OrderWithCartItems>>

    @Transaction
    @Query("SELECT * FROM orders WHERE orderId = :orderId")
    fun getOrderWithCartItemsById(orderId: Int): Flow<OrderWithCartItems>



    @Query("DELETE FROM orders")
    suspend fun clearOrders()

}