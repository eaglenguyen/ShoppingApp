package com.example.shoppingapp.data.repository

import android.content.SharedPreferences
import com.example.shoppingapp.data.mapper.toProduct
import com.example.shoppingapp.data.mapper.toProductsEntity
import com.example.shoppingapp.data.remote.StoreApi
import com.example.shoppingapp.data.remote.local.ProductsDao
import com.example.shoppingapp.domain.model.Product
import com.example.shoppingapp.domain.repository.ProductsRepository
import com.example.shoppingapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val api: StoreApi,
    private val dao: ProductsDao,
    private val prefs: SharedPreferences
): ProductsRepository {
    override fun getProductsList(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())

        try {
            val fetchApi = api.getProducts()
            dao.insert(fetchApi.toProductsEntity())

        } catch (e: HttpException){
            emit(Resource.Error(message = "Something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Could not reach server"))
        }

        val localCache = dao.getProducts()
        emit(Resource.Success(localCache.toProduct()))
    }

    override suspend fun signOut(): Resource<Unit> {
        return try {
            prefs.edit().remove("jwt").apply()
            Resource.Unauthorized()
        } catch (e: HttpException){
            Resource.Error("Error")
        }
    }

}