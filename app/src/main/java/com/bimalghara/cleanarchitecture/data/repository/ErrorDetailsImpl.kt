package com.bimalghara.cleanarchitecture.data.repository

import com.bimalghara.cleanarchitecture.data.error.ErrorDetails
import com.bimalghara.cleanarchitecture.data.error.mapper.ErrorMapperSource
import com.bimalghara.cleanarchitecture.domain.repository.ErrorDetailsSource
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

class ErrorDetailsImpl @Inject constructor(private val errorMapperSource: ErrorMapperSource) : ErrorDetailsSource {

    override suspend fun getErrorDetails(errorCode: Int): ErrorDetails {
        return errorMapperSource.getErrorByCode(errorCode)
    }

}