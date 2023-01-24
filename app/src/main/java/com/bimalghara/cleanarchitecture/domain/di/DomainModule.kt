package com.bimalghara.cleanarchitecture.domain.di

import com.bimalghara.cleanarchitecture.domain.repository.AuthRepositorySource
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
import com.bimalghara.cleanarchitecture.domain.repository.ErrorDetailsSource
import com.bimalghara.cleanarchitecture.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.coroutines.CoroutineContext

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
    fun provideRegisterOrLoginUseCase(coroutineContext: CoroutineContext, authRepositorySource: AuthRepositorySource): RegisterOrLoginUseCase{
        return RegisterOrLoginUseCase(ioDispatcher = coroutineContext, authRepositorySource = authRepositorySource)
    }


    @Provides
    fun provideGetAllRegisteredUsersUseCase(authRepositorySource: AuthRepositorySource): GetAllRegisteredUsersUseCase{
        return GetAllRegisteredUsersUseCase(authRepositorySource = authRepositorySource)
    }

    @Provides
    fun provideGetUserSessionUseCase(coroutineContext: CoroutineContext, authRepositorySource: AuthRepositorySource): GetUserSessionUseCase{
        return GetUserSessionUseCase(ioDispatcher = coroutineContext, authRepositorySource = authRepositorySource)
    }

    @Provides
    fun provideGetCountryListUseCase(coroutineContext: CoroutineContext, countryRepositorySource: CountryRepositorySource): GetCountryListUseCase{
        return GetCountryListUseCase(ioDispatcher = coroutineContext, countryRepositorySource = countryRepositorySource)
    }


}