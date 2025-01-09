package com.example.shoppingapp.data.remote

import com.example.shoppingapp.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {

    companion object {
        const val BASE_URL = "https://fakestoreapi.com"
    }



    @GET("/products")
    suspend fun getProducts(
    ): List<Product>

    @GET("/products/{id}")
    suspend fun getProduct(
        @Path("id") id: Int
    ): Product



}