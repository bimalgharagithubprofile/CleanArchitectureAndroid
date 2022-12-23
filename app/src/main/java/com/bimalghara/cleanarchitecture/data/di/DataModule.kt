package com.bimalghara.cleanarchitecture.data.di

import com.bimalghara.cleanarchitecture.data.repository.CountryRepositoryImpl
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
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

    @Binds
    @Singleton
    abstract fun provideCountryRepository(countryRepository: CountryRepositoryImpl): CountryRepositorySource

}