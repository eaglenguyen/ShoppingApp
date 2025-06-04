package com.example.shoppingapp.domain.model.address

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: AddressEntity)

    // ORDER BY id DESC LIMIT 1 returns only 1 latest address entry row, simulating overwriting old entry
    @Query("SELECT * FROM address ORDER BY id DESC LIMIT 1")
    fun getLatestAddress(): Flow<AddressEntity?>
}