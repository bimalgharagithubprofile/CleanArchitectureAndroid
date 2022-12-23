package com.bimalghara.cleanarchitecture.data.error.mapper

import android.content.Context
import com.bimalghara.cleanarchitecture.R
import com.bimalghara.cleanarchitecture.data.error.NETWORK_ERROR
import com.bimalghara.cleanarchitecture.data.error.NO_INTERNET_CONNECTION
import com.bimalghara.cleanarchitecture.data.error.PASS_WORD_ERROR
import com.bimalghara.cleanarchitecture.data.error.USER_NAME_ERROR
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMapper @Inject constructor(@ApplicationContext val context: Context) :
    ErrorMapperSource {

    override fun getErrorString(errorId: Int): String {
        return context.getString(errorId)
    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
            Pair(NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
            Pair(NETWORK_ERROR, getErrorString(R.string.network_error)),

            Pair(PASS_WORD_ERROR, getErrorString(R.string.invalid_password)),
            Pair(USER_NAME_ERROR, getErrorString(R.string.invalid_username)),

        ).withDefault { getErrorString(R.string.network_error) }
}
