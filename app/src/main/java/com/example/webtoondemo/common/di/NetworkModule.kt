package com.example.webtoondemo.common.di

import com.example.webtoondemo.common.remote.INetworkUseCase
import com.example.webtoondemo.common.remote.NetworkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideNetworkUseCase(
        okHttpClient: OkHttpClient
    ): INetworkUseCase = NetworkUseCase(okHttpClient)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
}