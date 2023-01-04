package com.bimalghara.cleanarchitecture.data.error.mapper

import com.bimalghara.cleanarchitecture.data.error.ErrorDetails

interface ErrorMapperSource {
    //fun getErrorString(errorId: Int): String

    fun getErrorByCode(errorCode: String): ErrorDetails

    val errorsMap: Map<String, String>
}
