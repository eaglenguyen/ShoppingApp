package com.example.shoppingapp.di

import com.example.shoppingapp.auth.AuthRepository
import com.example.shoppingapp.auth.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl
}