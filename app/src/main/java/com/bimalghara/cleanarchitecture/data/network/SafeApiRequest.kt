package com.bimalghara.cleanarchitecture.data.network

import android.util.Log
import com.bimalghara.cleanarchitecture.data.error.ERROR_NETWORK_ERROR
import com.bimalghara.cleanarchitecture.data.error.ERROR_NO_INTERNET_CONNECTION
import com.bimalghara.cleanarchitecture.utils.NetworkConnectivitySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import retrofit2.HttpException

/**
 * Created by BimalGhara
 */

abstract class SafeApiRequest(private val networkConnectivitySource: NetworkConnectivitySource) {

    //Expect DTO
    suspend fun<T> apiRequest(call: suspend () -> T): ResourceWrapper<T> {
        if (!networkConnectivitySource.isConnected()) {
            return ResourceWrapper.Error(errorCode = ERROR_NO_INTERNET_CONNECTION)
        }

        return try{
            ResourceWrapper.Success(data = call.invoke())
        }catch (throwable: Throwable) {
            Log.e("REST_API_Exc", "ERROR api invoke() => ${throwable.printStackTrace()}")
            when (throwable) {
                is HttpException -> {
                    ResourceWrapper.Error(errorCode = throwable.code())
                }
                else -> {
                    ResourceWrapper.Error(errorCode = ERROR_NETWORK_ERROR)
                }
            }
        }
    }

    //Expect Response [retrofit]
    /*suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivitySource.isConnected()) {
            return ERROR_NO_INTERNET_CONNECTION
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
            ERROR_NETWORK_ERROR
        }
    }*/
}