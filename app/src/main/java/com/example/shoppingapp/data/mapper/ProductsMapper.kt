package com.example.shoppingapp.data.mapper

import com.example.shoppingapp.data.remote.local.ProductsEntity
import com.example.shoppingapp.domain.model.Products

fun ProductsEntity.toProducts(): Products {
    return Products(
        products = products
    )
}

fun Products.toProductsEntity(): ProductsEntity {
    return ProductsEntity(
        products = products
    )
}