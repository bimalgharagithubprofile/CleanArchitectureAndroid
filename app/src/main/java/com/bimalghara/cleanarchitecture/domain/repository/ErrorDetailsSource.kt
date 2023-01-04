package com.bimalghara.cleanarchitecture.domain.repository

import com.bimalghara.cleanarchitecture.data.error.ErrorDetails

/**
 * Created by BimalGhara
 */

interface ErrorDetailsSource {
    suspend fun getErrorDetails(cause: String): ErrorDetails
}