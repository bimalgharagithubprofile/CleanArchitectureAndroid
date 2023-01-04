package com.bimalghara.cleanarchitecture.data.network

import android.util.Log
import com.bimalghara.cleanarchitecture.data.error.CustomException
import com.bimalghara.cleanarchitecture.data.error.ERROR_NETWORK_ERROR
import com.bimalghara.cleanarchitecture.data.error.ERROR_SOCKET_TIMEOUT
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * Created by BimalGhara
 */

abstract class SafeApiRequest {

    //Expect DTO
    suspend fun<T> apiRequest(call: suspend () -> T): T {
        return try{
            call.invoke()
        }catch (throwable: Throwable) {
            Log.e("REST_API_Exc", "ERROR api invoke() => ${throwable.printStackTrace()}")
            when (throwable) {
                is HttpException -> {
                    throw CustomException(cause = throwable.code().toString())
                }
                is SocketTimeoutException -> {
                    throw CustomException(cause = ERROR_SOCKET_TIMEOUT)
                }
                else -> {
                    throw CustomException(cause = ERROR_NETWORK_ERROR)
                }
            }
        }
    }

    //Expect Response [retrofit]
    /*suspend fun apiRequest(call: suspend () -> Response<*>): Any? {
        if (!networkConnectivitySource.isConnected()) {
            return ERROR_NO_INTERNET_CONNECTION
        }
        return try {
            val response = call.invoke()
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