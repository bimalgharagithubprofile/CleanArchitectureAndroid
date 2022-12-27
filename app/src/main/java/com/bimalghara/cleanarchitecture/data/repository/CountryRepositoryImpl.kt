package com.bimalghara.cleanarchitecture.data.repository

import com.bimalghara.cleanarchitecture.data.error.ERROR_DEFAULT
import com.bimalghara.cleanarchitecture.data.mapper.toDomain
import com.bimalghara.cleanarchitecture.data.network.RemoteDataImpl
import com.bimalghara.cleanarchitecture.domain.model.country.Country
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import javax.inject.Inject


/**
 * Created by BimalGhara
 */

class CountryRepositoryImpl @Inject constructor(private val remoteRepository: RemoteDataImpl) : CountryRepositorySource {

    override suspend fun getCountryList(): ResourceWrapper<List<Country>> {

        return when(val data = remoteRepository.requestCountries()){
            is ResourceWrapper.Success -> {
                ResourceWrapper.Success(data = data.data?.map { it.toDomain() })
            }
            else -> {
                ResourceWrapper.Error(errorCode = data.errorCode ?: ERROR_DEFAULT)
            }
        }
    }

}