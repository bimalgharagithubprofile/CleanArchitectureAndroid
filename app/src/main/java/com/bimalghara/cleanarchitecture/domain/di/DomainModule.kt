package com.bimalghara.cleanarchitecture.domain.di

import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
import com.bimalghara.cleanarchitecture.domain.use_case.GetCountryListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by BimalGhara
 */

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {


    @Provides
    fun provideGetCountryListUseCase(countryRepositorySource: CountryRepositorySource): GetCountryListUseCase{
        return GetCountryListUseCase(countryRepositorySource = countryRepositorySource)
    }


}