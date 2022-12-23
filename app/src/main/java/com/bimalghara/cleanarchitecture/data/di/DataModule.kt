package com.bimalghara.cleanarchitecture.data.di

import com.bimalghara.cleanarchitecture.data.error.mapper.ErrorMapperImpl
import com.bimalghara.cleanarchitecture.data.error.mapper.ErrorMapperSource
import com.bimalghara.cleanarchitecture.data.repository.CountryRepositoryImpl
import com.bimalghara.cleanarchitecture.data.repository.ErrorDetailsImpl
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
import com.bimalghara.cleanarchitecture.domain.repository.ErrorDetailsSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by BimalGhara
 */

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