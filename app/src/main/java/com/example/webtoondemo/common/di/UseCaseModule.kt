package com.example.webtoondemo.common.di

import com.example.webtoondemo.common.remote.INetworkUseCase
import com.example.webtoondemo.home.model.HomeApi
import com.example.webtoondemo.home.model.HomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @Provides
    fun provideHomeUseCase(
        networkUseCase: INetworkUseCase
    ): HomeUseCase = HomeApi(networkUseCase)
}
