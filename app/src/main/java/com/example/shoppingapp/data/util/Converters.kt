package com.example.shoppingapp.data.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.shoppingapp.domain.model.Product
import com.example.shoppingapp.domain.model.Rates
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {


    // TypeConverter for `Rates`
    @TypeConverter
    fun fromRatesJson(json: String): Rates? {
        return jsonParser.fromJson(
            json,
            object : TypeToken<Rates>() {}.type
        )
    }

    @TypeConverter
    fun toRatesJson(rates: Rates?): String {
        return jsonParser.toJson(
            rates,
            object : TypeToken<Rates>() {}.type
        ) ?: ""
    }
}

