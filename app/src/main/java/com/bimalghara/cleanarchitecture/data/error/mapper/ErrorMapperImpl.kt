package com.bimalghara.cleanarchitecture.data.error.mapper

import android.content.Context
import com.bimalghara.cleanarchitecture.R
import com.bimalghara.cleanarchitecture.data.error.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMapperImpl @Inject constructor(@ApplicationContext val context: Context) :
    ErrorMapperSource {



    override fun getErrorByCode(errorCode: Int): ErrorDetails {
        return ErrorDetails(code = errorCode, description = errorsMap.getValue(errorCode))
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
            Pair(ERROR_NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
            Pair(ERROR_NETWORK_ERROR, getErrorString(R.string.network_error)),
            Pair(ERROR_SOCKET_TIMEOUT, getErrorString(R.string.socket_timeout)),

            Pair(ERROR_USER_NAME_ERROR, getErrorString(R.string.invalid_username)),
            Pair(ERROR_PASS_WORD_ERROR, getErrorString(R.string.invalid_password)),
            Pair(ERROR_CHECK_YOUR_FIELDS, getErrorString(R.string.empty_fields)),

        ).withDefault { "Oops! Something went wrong" }

    private fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }
}
