package com.bimalghara.cleanarchitecture.data.repository

import com.bimalghara.cleanarchitecture.data.error.CustomException
import com.bimalghara.cleanarchitecture.data.error.ErrorDetails
import com.bimalghara.cleanarchitecture.data.mapper.toDomain
import com.bimalghara.cleanarchitecture.data.network.RemoteDataSource
import com.bimalghara.cleanarchitecture.domain.model.country.Country
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import javax.inject.Inject


/**
 * Created by BimalGhara
 */

class CountryRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    CountryRepositorySource {

    override suspend fun getCountryList(): List<Country> {
        return try {
            val data = remoteDataSource.requestCountries()

            data.map { it.toDomain() }
        } catch (e: CustomException) {
            throw e
        }
    }
}

