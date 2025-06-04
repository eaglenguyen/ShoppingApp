package com.example.shoppingapp.data.repository

import com.example.shoppingapp.domain.model.address.AddressDao
import com.example.shoppingapp.domain.model.address.AddressEntity
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