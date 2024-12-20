package com.example.shoppingapp.domain.repository

import com.example.shoppingapp.domain.model.Products
import com.example.shoppingapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProductsList(): Flow<Resource<Products>>
}