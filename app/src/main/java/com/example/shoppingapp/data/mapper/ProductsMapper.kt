package com.example.shoppingapp.data.mapper

import com.example.shoppingapp.data.remote.local.ProductsEntity
import com.example.shoppingapp.domain.model.Product

fun List<ProductsEntity>.toProduct(): List<Product> {
    return this.map {
        Product(
            id = it.id,
            title = it.title,
            price = it.price,
            description = it.description,
            category = it.category,
            image = it.image,
            rating = it.rating
        )
    }
}

fun List<Product>.toProductsEntity(): List<ProductsEntity> {
    return this.map {
        return this.map {
            ProductsEntity(
                id = it.id,
                title = it.title,
                price = it.price,
                description = it.description,
                category = it.category,
                image = it.image,
                rating = it.rating
            )
        }
    }
}