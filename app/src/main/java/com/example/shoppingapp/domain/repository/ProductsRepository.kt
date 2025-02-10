package com.example.shoppingapp.domain.repository

import androidx.room.Query
import com.example.shoppingapp.domain.model.Product
import com.example.shoppingapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun getProductsList(
        query: String
    ): Flow<Resource<List<Product>>>

    suspend fun getProduct(
        id: Int
    ): Resource<Product>


    suspend fun signOut(): Resource<Unit>

}