package com.example.shoppingapp.data.remote

import com.example.shoppingapp.domain.model.Products
import retrofit2.http.GET

interface StoreApi {

    companion object {
        const val BASE_URL = "https://fakestoreapi.com"
    }

    @GET("/products")
    suspend fun getProducts(
    ): Products




}