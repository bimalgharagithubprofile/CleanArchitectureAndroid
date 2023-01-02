package com.bimalghara.cleanarchitecture.common.di

import android.content.Context
import com.bimalghara.cleanarchitecture.utils.NetworkConnectivity
import com.bimalghara.cleanarchitecture.utils.NetworkConnectivitySource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/* instantiate class */
@InstallIn(SingletonComponent::class)
@Module
class AppModuleNetworkConnectivity {

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivitySource {
        return NetworkConnectivity(context)
    }
}