package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.mapper.toProduct
import com.example.shoppingapp.data.mapper.toProductsEntity
import com.example.shoppingapp.data.remote.StoreApi
import com.example.shoppingapp.data.remote.local.ProductsDao
import com.example.shoppingapp.domain.model.Product
import com.example.shoppingapp.domain.repository.ProductsRepository
import com.example.shoppingapp.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val api: StoreApi,
    private val dao: ProductsDao,
): ProductsRepository {


    override fun getProductsList(
        query: String
    ): Flow<Resource<List<Product>>> = flow {


        emit(Resource.Loading(true))
        delay(500)
        val searchProducts = dao.searchProductTitle(query)
        emit(Resource.Success(searchProducts.toProduct()))

        val isDbEmpty = searchProducts.isEmpty() && query.isBlank()
        if (!isDbEmpty) {
            emit(Resource.Loading(false))
            return@flow
        }

        // Step 1: Emit cached data from Room immediately
        val localCache = dao.getProducts()
        if (localCache.isNotEmpty()) {
            emit(Resource.Success(localCache.toProduct()))

        } else {
            emit(Resource.Error(message = "No cached data available"))
        }

        try {
            val fetchApi = api.getProducts()
            dao.insert(fetchApi.toProductsEntity())


            val updatedCache = dao.getProducts()
            emit(Resource.Success(updatedCache.toProduct()))

        } catch (e: HttpException){
            emit(Resource.Error(message = "Something went wrong"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Could not reach server"))
        }

        emit(Resource.Loading(false)) // 🔹 Always stop loading at the end!


    }

    override suspend fun getProduct(id: Int): Resource<Product> {
        return try {
            val result = api.getProduct(id)
            Resource.Success(result)
        }  catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load Product Details")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load Product Details")

        }
    }





}