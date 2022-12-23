package com.bimalghara.cleanarchitecture.data.network


import com.bimalghara.cleanarchitecture.data.error.NETWORK_ERROR
import com.bimalghara.cleanarchitecture.data.error.NO_INTERNET_CONNECTION
import com.bimalghara.cleanarchitecture.data.network.retrofit.ApiServiceGenerator
import com.bimalghara.cleanarchitecture.domain.model.Country
import com.bimalghara.cleanarchitecture.utils.NetworkConnectivitySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by BimalGhara
 */

class RemoteDataImpl @Inject constructor(private val serviceGenerator: ApiServiceGenerator, private val networkConnectivitySource: NetworkConnectivitySource) : RemoteDataSource {

    override suspend fun requestCountries(): ResourceWrapper<Country> {
        /*val recipesService = serviceGenerator.createApiService(ApiServiceCountries::class.java)
        return when (val response = processCall(recipesService::fetchRecipes)) {
            is List<*> -> {
                ResourceWrapper.Success(data = Country(response as ArrayList<Country>))
            }
            else -> {
                ResourceWrapper.Error(errorMessage = "")
            }
        }*/
        return ResourceWrapper.Error(errorMessage = "")
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivitySource.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
