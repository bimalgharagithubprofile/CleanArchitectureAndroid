package com.bimalghara.cleanarchitecture.data.network


import com.bimalghara.cleanarchitecture.data.model.country.CountryDTO
import com.bimalghara.cleanarchitecture.data.network.retrofit.ApiServiceGenerator
import com.bimalghara.cleanarchitecture.data.network.retrofit.service.ApiServiceCountries
import com.bimalghara.cleanarchitecture.utils.NetworkConnectivitySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

class RemoteDataImpl @Inject constructor(
    private val serviceGenerator: ApiServiceGenerator,
    networkConnectivitySource: NetworkConnectivitySource
) : RemoteDataSource, SafeApiRequest(networkConnectivitySource) {

    override suspend fun requestCountries(): ResourceWrapper<List<CountryDTO>> {
        val recipesService = serviceGenerator.createApiService(ApiServiceCountries::class.java)

        return apiRequest(recipesService::getCountryList)
    }


}
