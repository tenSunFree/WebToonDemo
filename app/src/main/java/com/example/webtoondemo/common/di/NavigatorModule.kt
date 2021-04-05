package com.example.webtoondemo.common.di

import com.example.webtoondemo.common.navigator.Navigator
import com.example.webtoondemo.common.navigator.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NavigatorModule {
    @Binds
    abstract fun provideNavigator(
        navigator: NavigatorImpl
    ): Navigator
}