package com.bimalghara.cleanarchitecture.data.network


import android.util.Log
import com.bimalghara.cleanarchitecture.data.error.ERROR_NETWORK_ERROR
import com.bimalghara.cleanarchitecture.data.error.ERROR_NO_INTERNET_CONNECTION
import com.bimalghara.cleanarchitecture.data.model.CountryDTO
import com.bimalghara.cleanarchitecture.data.network.retrofit.ApiServiceGenerator
import com.bimalghara.cleanarchitecture.data.network.retrofit.service.ApiServiceCountries
import com.bimalghara.cleanarchitecture.utils.NetworkConnectivitySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
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
