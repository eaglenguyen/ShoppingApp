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


    @Query("DELETE FROM cart WHERE id IN (SELECT id FROM cart LIMIT 1 OFFSET :position)")
    suspend fun deleteId(position: Int)




    @Query("SELECT * FROM cart WHERE id = :id LIMIT 1")
    suspend fun getCartItemById(id: Int): Cart?

    @Query("SELECT * from cart")
    fun getCart(): Flow<List<Cart>>

    // After converting the price: String into REAL (floating-point number aka decimals) same as Double in Kotlin
    // Gets the total sum value from the cart table,
    @Query("SELECT SUM(CAST(price AS REAL)) FROM cart")
    fun getTotalPrice(): Flow<Double?>


    @Query("SELECT SUM(quantity) FROM cart")
    fun getTotalQuantity(): Flow<Int?>



    @Query("""
        UPDATE cart 
        SET quantity = quantity + 1,
            price = CAST(price AS REAL) + :itemPrice
        WHERE id = :id
    """)
    suspend fun increaseQuantity(id: Int, itemPrice: Double)




    @Query("""
        UPDATE cart 
        SET quantity = quantity + 1,
                    price = CAST(price AS REAL) + :itemPrice
        WHERE id = (SELECT id FROM cart LIMIT 1 OFFSET :position)
    """)
    suspend fun increaseQuantityCart(position: Int, itemPrice: Double)




    @Query("""
        UPDATE cart 
        SET quantity = quantity - 1,
                    price = CAST(price AS REAL) - :itemPrice
        WHERE id = (SELECT id FROM cart LIMIT 1 OFFSET :position)
        AND quantity > 0

    """)
    suspend fun removeQuantityCart(position: Int, itemPrice: Double)

    // removing item once it hits zero only shows after recomposition
    @Query("""
    DELETE FROM cart 
    WHERE id = (SELECT id FROM cart LIMIT 1 OFFSET :position) 
    AND quantity <= 0
""")
    suspend fun deleteZeroQuantityItems(position: Int)











}