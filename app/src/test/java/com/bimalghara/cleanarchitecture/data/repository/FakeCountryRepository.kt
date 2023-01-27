package com.bimalghara.cleanarchitecture.data.repository

import com.bimalghara.cleanarchitecture.DataStatus
import com.bimalghara.cleanarchitecture.FailureType
import com.bimalghara.cleanarchitecture.TestUtil.dataStatus
import com.bimalghara.cleanarchitecture.TestUtil.failureType
import com.bimalghara.cleanarchitecture.data.error.CustomException
import com.bimalghara.cleanarchitecture.data.error.ERROR_NETWORK_ERROR
import com.bimalghara.cleanarchitecture.data.error.ERROR_SOCKET_TIMEOUT
import com.bimalghara.cleanarchitecture.domain.model.country.Country
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource

class FakeCountryRepository : CountryRepositorySource {

    override suspend fun getCountryList(): List<Country> {
        when (dataStatus) {
            DataStatus.EmptyResponse -> {
                return emptyList()
            }
            DataStatus.Success -> {
                return listOf(
                    Country(name = "India", url = "https://abc.com"),
                    Country(name = "Malaysia", url = "https://efg.com")
                )
            }
            else -> {
                when (failureType) {
                    is FailureType.Http -> {
                        throw CustomException(cause = "401")
                    }
                    is FailureType.Timeout -> {
                        throw CustomException(cause = ERROR_SOCKET_TIMEOUT)
                    }
                    else -> {
                        throw CustomException(cause = ERROR_NETWORK_ERROR)
                    }
                }
            }
        }
    }

}