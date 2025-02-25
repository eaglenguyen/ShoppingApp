package com.example.shoppingapp.data.remote.localcart

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cart: Cart)

    @Update
    suspend fun update(cart: Cart)

    @Delete
    suspend fun delete(cart: Cart)


    @Query("SELECT * FROM cart WHERE id = :id LIMIT 1")
    suspend fun getCartItemById(id: Int): Cart?




    @Query("""
        UPDATE cart 
        SET quantity = quantity + 1
        WHERE id = :id
    """)
    suspend fun increaseQuantity(id: Int)




    @Query("""
        UPDATE cart 
        SET quantity = quantity + 1
        WHERE id = (SELECT id FROM cart LIMIT 1 OFFSET :position)
    """)
    suspend fun increaseQuantityCart(position: Int)




    @Query("""
        UPDATE cart 
        SET quantity = quantity - 1
        WHERE id = (SELECT id FROM cart LIMIT 1 OFFSET :position)
        AND quantity > 0

    """)
    suspend fun removeQuantityCart(position: Int)


    @Query("""
    DELETE FROM cart 
    WHERE quantity <= 0
""")
    suspend fun deleteZeroQuantityItems()

    // removing item once it hits zero not working



    @Query("DELETE FROM cart WHERE id IN (SELECT id FROM cart LIMIT 1 OFFSET :position)")
    suspend fun deleteId(position: Int)




    @Query("SELECT * from cart")
    fun getCart(): Flow<List<Cart>>

    // After converting the price: String into REAL (floating-point number aka decimals) same as Double in Kotlin
    // Gets the total sum value from the cart table,
    @Query("SELECT SUM(CAST(price AS REAL)) FROM cart")
    fun getTotalPrice(): Flow<Double?>




}