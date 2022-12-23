package com.bimalghara.cleanarchitecture.utils

/**
 * Created by BimalGhara
 */
sealed class ResourceWrapper<T>(val data:T?=null, val errorCode:Int?=null){

    class Loading<T> : ResourceWrapper<T>()
    class Success<T>(data: T?): ResourceWrapper<T>(data=data)
    class Error<T>(errorCode: Int) : ResourceWrapper<T>(errorCode = errorCode)

    override fun toString(): String {
        return when (this){
            is Loading -> "Loading"
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[Exception=$errorCode]"
        }
    }
}
