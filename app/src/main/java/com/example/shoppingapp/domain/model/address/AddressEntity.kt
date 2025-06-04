package com.example.shoppingapp.domain.model.address

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class AddressEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val address: String,
    val city: String,
    val state: String,
    val zipcode: String
)
