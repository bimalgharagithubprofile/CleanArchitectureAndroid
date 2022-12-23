package com.bimalghara.cleanarchitecture.data.repository

import com.bimalghara.cleanarchitecture.data.network.RemoteDataImpl
import com.bimalghara.cleanarchitecture.domain.model.Country
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
import javax.inject.Inject


/**
 * Created by BimalGhara
 */

class CountryRepositoryImpl @Inject constructor(private val remoteRepository: RemoteDataImpl) : CountryRepositorySource {

    override suspend fun getCountryList(): List<Country> {
        TODO("Not yet implemented")
    }

}