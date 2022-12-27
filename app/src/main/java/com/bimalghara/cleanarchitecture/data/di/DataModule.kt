package com.bimalghara.cleanarchitecture.data.di

import android.content.Context
import com.bimalghara.cleanarchitecture.data.error.mapper.ErrorMapperImpl
import com.bimalghara.cleanarchitecture.data.error.mapper.ErrorMapperSource
import com.bimalghara.cleanarchitecture.data.local.LocalDataImpl
import com.bimalghara.cleanarchitecture.data.local.LocalDataSource
import com.bimalghara.cleanarchitecture.data.network.RemoteDataImpl
import com.bimalghara.cleanarchitecture.data.network.RemoteDataSource
import com.bimalghara.cleanarchitecture.data.network.retrofit.ApiServiceGenerator
import com.bimalghara.cleanarchitecture.data.repository.CountryRepositoryImpl
import com.bimalghara.cleanarchitecture.data.repository.ErrorDetailsImpl
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
import com.bimalghara.cleanarchitecture.domain.repository.ErrorDetailsSource
import com.bimalghara.cleanarchitecture.utils.NetworkConnectivity
import com.bimalghara.cleanarchitecture.utils.NetworkConnectivitySource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by BimalGhara
 */

/* instantiate interfaces */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    //error for the app
    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapperImpl): ErrorMapperSource
    @Binds
    @Singleton
    abstract fun provideErrorDetails(errorDetails: ErrorDetailsImpl): ErrorDetailsSource


    @Binds
    @Singleton
    abstract fun provideCountryRepository(countryRepository: CountryRepositoryImpl): CountryRepositorySource

}



/* instantiate class */
@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivitySource {
        return NetworkConnectivity(context)
    }

    @Provides
    @Singleton
    fun provideRemoteData(serviceGenerator: ApiServiceGenerator, networkConnectivitySource: NetworkConnectivitySource): RemoteDataSource {
        return RemoteDataImpl(serviceGenerator, networkConnectivitySource)
    }

    @Provides
    @Singleton
    fun provideLocalData(@ApplicationContext context: Context): LocalDataSource {
        return LocalDataImpl(context)
    }
}