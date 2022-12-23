package com.bimalghara.cleanarchitecture.domain.use_case

import com.bimalghara.cleanarchitecture.data.error.Error

/**
 * Created by BimalGhara
 */

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
