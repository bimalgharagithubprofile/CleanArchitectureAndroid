package com.bimalghara.cleanarchitecture.data.repository

import com.bimalghara.cleanarchitecture.data.error.ERROR_AUTH_FAILED
import com.bimalghara.cleanarchitecture.data.local.LocalDataSource
import com.bimalghara.cleanarchitecture.data.model.auth.AuthData
import com.bimalghara.cleanarchitecture.domain.repository.AuthRepositorySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val localData: LocalDataSource) : AuthRepositorySource {

    override suspend fun registerOrLogin(
        firstName: String,
        lastName: String
    ): ResourceWrapper<Long> {
        val auth = AuthData(
            firstName = firstName,
            lastName = lastName
        )
        return when (val response = localData.saveUserData(auth)){
            is Long -> {
                ResourceWrapper.Success(data = response)
            }
            else -> {
                ResourceWrapper.Error(errorCode = ERROR_AUTH_FAILED)
            }
        }
    }

    override suspend fun getUserData(): ResourceWrapper<AuthData> {
        return ResourceWrapper.Success(data = localData.getUserData())
    }

    override suspend fun getLastSession(): Long {
        return localData.getLastLoginSession()
    }
}