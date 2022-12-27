package com.bimalghara.cleanarchitecture.domain.di

import com.bimalghara.cleanarchitecture.domain.repository.AuthRepositorySource
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
import com.bimalghara.cleanarchitecture.domain.repository.ErrorDetailsSource
import com.bimalghara.cleanarchitecture.domain.use_case.GetCountryListUseCase
import com.bimalghara.cleanarchitecture.domain.use_case.GetErrorDetailsUseCase
import com.bimalghara.cleanarchitecture.domain.use_case.RegisterOrLoginUseCase
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
    fun provideGetErrorDetailsUseCase(errorDetailsSource: ErrorDetailsSource): GetErrorDetailsUseCase{
        return GetErrorDetailsUseCase(errorDetailsSource = errorDetailsSource)
    }

    @Provides
    fun provideRegisterOrLoginUseCase(authRepositorySource: AuthRepositorySource): RegisterOrLoginUseCase{
        return RegisterOrLoginUseCase(authRepositorySource = authRepositorySource)
    }

    @Provides
    fun provideGetCountryListUseCase(countryRepositorySource: CountryRepositorySource): GetCountryListUseCase{
        return GetCountryListUseCase(countryRepositorySource = countryRepositorySource)
    }


}