package com.bimalghara.cleanarchitecture.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bimalghara.cleanarchitecture.data.error.mapper.ErrorMapperImpl
import com.bimalghara.cleanarchitecture.data.error.mapper.ErrorMapperSource
import com.bimalghara.cleanarchitecture.data.local.preferences.DataStoreImpl
import com.bimalghara.cleanarchitecture.data.local.preferences.DataStoreSource
import com.bimalghara.cleanarchitecture.data.local.LocalDataImpl
import com.bimalghara.cleanarchitecture.data.local.LocalDataSource
import com.bimalghara.cleanarchitecture.data.local.database.AppDatabase
import com.bimalghara.cleanarchitecture.data.network.RemoteDataImpl
import com.bimalghara.cleanarchitecture.data.network.RemoteDataSource
import com.bimalghara.cleanarchitecture.data.network.retrofit.ApiServiceGenerator
import com.bimalghara.cleanarchitecture.data.repository.AuthRepositoryImpl
import com.bimalghara.cleanarchitecture.data.repository.CountryRepositoryImpl
import com.bimalghara.cleanarchitecture.data.repository.ErrorDetailsImpl
import com.bimalghara.cleanarchitecture.domain.repository.AuthRepositorySource
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
import com.bimalghara.cleanarchitecture.domain.repository.ErrorDetailsSource
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

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModuleErrors {

    //error for the app
    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapperImpl): ErrorMapperSource
    @Binds
    @Singleton
    abstract fun provideErrorDetails(errorDetails: ErrorDetailsImpl): ErrorDetailsSource

}



@Module
@InstallIn(SingletonComponent::class)
abstract class DataModuleRepositories {

    @Binds
    @Singleton
    abstract fun provideAuthRepository(authRepository: AuthRepositoryImpl): AuthRepositorySource

    @Binds
    @Singleton
    abstract fun provideCountryRepository(countryRepository: CountryRepositoryImpl): CountryRepositorySource

}



@InstallIn(SingletonComponent::class)
@Module
class DataModuleDataSources {

    @Provides
    @Singleton
    fun providePreferenceDataStore(@ApplicationContext appContext: Context): DataStoreSource {
        return DataStoreImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalData(@ApplicationContext context: Context, dataStore: DataStoreSource, db: AppDatabase): LocalDataSource {
        return LocalDataImpl(context, dataStore, db.authDao)
    }

    @Provides
    @Singleton
    fun provideRemoteData(serviceGenerator: ApiServiceGenerator): RemoteDataSource {
        return RemoteDataImpl(serviceGenerator)
    }
}