package com.bimalghara.cleanarchitecture.data.error

/**
 * Created by BimalGhara
 */

class ErrorDetails(val code: Int, val description: String) {
    /*constructor(exception: Exception) : this(code = DEFAULT_ERROR, description = exception.message
            ?: "")*/
}

const val ERROR_DEFAULT = -7000

const val ERROR_NO_INTERNET_CONNECTION = -7001
const val ERROR_NETWORK_ERROR = -7002
const val ERROR_NETWORK_ERROR_404 = -7003
const val ERROR_NETWORK_ERROR_405 = -7005

const val ERROR_PASS_WORD_ERROR = -7111
const val ERROR_USER_NAME_ERROR = -7112
