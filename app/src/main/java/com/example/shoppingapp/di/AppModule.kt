package com.example.shoppingapp.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.shoppingapp.auth.AuthApi
import com.example.shoppingapp.auth.AuthRepository
import com.example.shoppingapp.auth.AuthRepositoryImpl
import com.example.shoppingapp.data.remote.StoreApi
import com.example.shoppingapp.data.remote.StoreApi.Companion.BASE_URL
import com.example.shoppingapp.domain.model.address.AddressDao
import com.example.shoppingapp.domain.model.address.AddressDatabase
import com.example.shoppingapp.domain.model.products.ProductsDatabase
import com.example.shoppingapp.domain.model.cart.CartDao
import com.example.shoppingapp.data.repository.AddressRepository
import com.example.shoppingapp.data.repository.CartRepository
import com.example.shoppingapp.data.repository.OrdersRepository
import com.example.shoppingapp.data.repository.ProductsRepositoryImpl
import com.example.shoppingapp.data.util.Converters
import com.example.shoppingapp.data.util.GsonParser
import com.example.shoppingapp.domain.model.orders.OrdersDao
import com.example.shoppingapp.domain.repository.ProductsRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providesAuthRepository(api: AuthApi, prefs: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl("http://192.168.50.31:8080/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }


    @Provides
    @Singleton
    fun provideStoreApi(): StoreApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StoreApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app:Application): ProductsDatabase {
        return Room.databaseBuilder(
            app,
            ProductsDatabase::class.java,
            "product_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAddressDatabase(app: Application): AddressDatabase {
        return Room.databaseBuilder(
            app,
            AddressDatabase::class.java,
            "address_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideDao(db: ProductsDatabase) = db.cartDao

    @Provides
    fun provideAddressDao(db: AddressDatabase) = db.addressDao

    @Provides
    fun provideOrdersDao(db: ProductsDatabase) = db.ordersDao



    @Provides
    @Singleton
    fun provideOrderRepository(ordersDao: OrdersDao) : OrdersRepository = OrdersRepository(ordersDao)

    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDao) : CartRepository = CartRepository(cartDao)

    @Provides
    @Singleton
    fun provideAddressRepository(addressDao: AddressDao) : AddressRepository = AddressRepository(addressDao)


    @Provides
    @Singleton
    fun provideProductsRepository(api: StoreApi, db: ProductsDatabase): ProductsRepository =  ProductsRepositoryImpl(api,db.productsDao)


}