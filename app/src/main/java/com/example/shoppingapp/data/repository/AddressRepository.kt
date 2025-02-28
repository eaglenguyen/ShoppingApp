package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.remote.address.AddressDao
import com.example.shoppingapp.data.remote.address.AddressEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddressRepository @Inject constructor(
    private val addressDao: AddressDao
) {

    suspend fun saveAddress(address: AddressEntity) {
        addressDao.insertAddress(address)
    }

    fun getSavedAddress(): Flow<AddressEntity?> = addressDao.getLatestAddress()



}