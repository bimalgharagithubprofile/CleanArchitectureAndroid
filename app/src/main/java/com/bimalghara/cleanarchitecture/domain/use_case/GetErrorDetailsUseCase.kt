package com.bimalghara.cleanarchitecture.domain.use_case

import com.bimalghara.cleanarchitecture.data.error.ErrorDetails
import com.bimalghara.cleanarchitecture.domain.repository.ErrorDetailsSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by BimalGhara
 */

class GetErrorDetailsUseCase(private val errorDetailsSource: ErrorDetailsSource) {

    suspend operator fun invoke(errorCode: Int): ErrorDetails = withContext(Dispatchers.IO){
        errorDetailsSource.getErrorDetails(errorCode)
    }
}