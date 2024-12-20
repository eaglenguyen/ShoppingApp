package com.example.shoppingapp.data.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.shoppingapp.domain.model.Product
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromDataJson(json: String): List<Product> {
        return jsonParser.fromJson<ArrayList<Product>>(
            json,
            object : TypeToken<ArrayList<Product>>(){}.type
        ) ?: emptyList()
    }

//    @TypeConverter
//    fun fromImageJson(json: String): List<Image> {
//        return jsonParser.fromJson<ArrayList<Image>>(
//            json,
//            object : TypeToken<ArrayList<Image>>(){}.type
//        ) ?: emptyList()
//    }

    @TypeConverter
    fun toDataJson (data: List<Product>) : String {
        return jsonParser.toJson(
            data,
            object : TypeToken<ArrayList<Product>>(){}.type
        ) ?: "[]"
    }

//    @TypeConverter
//    fun toImageJson (data: List<Image>) : String {
//        return jsonParser.toJson(
//            data,
//            object : TypeToken<ArrayList<Image>>(){}.type
//        ) ?: "[]"
//    }
}