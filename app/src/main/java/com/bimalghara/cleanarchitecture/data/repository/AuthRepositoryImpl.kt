package com.bimalghara.cleanarchitecture.data.repository

import com.bimalghara.cleanarchitecture.data.error.ERROR_AUTH_FAILED
import com.bimalghara.cleanarchitecture.data.local.LocalDataSource
import com.bimalghara.cleanarchitecture.data.model.auth.AuthData
import com.bimalghara.cleanarchitecture.domain.repository.AuthRepositorySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) : AuthRepositorySource {

    override suspend fun registerOrLogin(
        username: String,
        password: String
    ): ResourceWrapper<Long> {

        delay(2000)//dummy delay


        val name = username.split("@")[0]
        val auth = AuthData(
            firstName = name,//mocked dummy data
            lastName = name,//mocked dummy data
            email = username
        )
        return when (val response = localDataSource.saveUserData(auth)){
            is Long -> {
                ResourceWrapper.Success(data = response)
            }
            else -> {
                ResourceWrapper.Error(errorCode = ERROR_AUTH_FAILED)
            }
        }
    }

    override suspend fun getUserData(): ResourceWrapper<AuthData> {
        return ResourceWrapper.Success(data = localDataSource.getUserData())
    }

    override suspend fun getLastSession(): Long {
        return localDataSource.getLastLoginSession()
    }
}