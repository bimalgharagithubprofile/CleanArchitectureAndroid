package com.bimalghara.cleanarchitecture.common.di

import android.content.Context
import com.bimalghara.cleanarchitecture.utils.NetworkConnectivityImpl
import com.bimalghara.cleanarchitecture.utils.NetworkConnectivitySource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/* instantiate class */
@InstallIn(SingletonComponent::class)
@Module
class AppModuleNetworkConnectivity {

    @Provides
    @Singleton
    fun provideCoroutineContextIO(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivitySource {
        return NetworkConnectivityImpl(context)
    }
}