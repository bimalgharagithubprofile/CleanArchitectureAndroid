package com.bimalghara.cleanarchitecture.data.repository

import com.bimalghara.cleanarchitecture.data.error.ErrorDetails
import com.bimalghara.cleanarchitecture.data.error.mapper.ErrorMapperSource
import com.bimalghara.cleanarchitecture.domain.repository.ErrorDetailsSource
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

class ErrorDetailsImpl @Inject constructor(private val errorMapperSource: ErrorMapperSource) : ErrorDetailsSource {

    override suspend fun getErrorDetails(cause: String): ErrorDetails {

        // 2 types of error:
        // 1 -> String-Resource  [ie: -7*** ]
        // 2 -> kotlin.Exception [ie: message as String]

        return if(cause.startsWith("-")){
            errorMapperSource.getErrorByCode(cause)
        } else {
            ErrorDetails(cause)
        }
    }

}