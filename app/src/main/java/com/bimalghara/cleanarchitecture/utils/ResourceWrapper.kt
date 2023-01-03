package com.bimalghara.cleanarchitecture.utils

import com.bimalghara.cleanarchitecture.data.error.ErrorDetails

/**
 * Created by BimalGhara
 */
sealed class ResourceWrapper<T>(val data:T?=null, val errorDetails: ErrorDetails?=null){

    class Loading<T> : ResourceWrapper<T>()
    class Success<T>(data: T?): ResourceWrapper<T>(data=data)
    class Error<T>(errorDetails: ErrorDetails) : ResourceWrapper<T>(errorDetails = errorDetails)

    override fun toString(): String {
        return when (this){
            is Loading -> "Loading"
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[Exception=${errorDetails?.code}::${errorDetails?.description}]"
        }
    }
}
