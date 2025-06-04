package com.example.shoppingapp.domain.model.address

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AddressEntity::class], version = 1, exportSchema = false)
abstract class AddressDatabase: RoomDatabase(){

    abstract val addressDao: AddressDao

}