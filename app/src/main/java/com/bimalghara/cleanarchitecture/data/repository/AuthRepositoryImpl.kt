package com.bimalghara.cleanarchitecture.data.repository

import com.bimalghara.cleanarchitecture.data.error.ERROR_AUTH_FAILED
import com.bimalghara.cleanarchitecture.data.local.LocalDataSource
import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData
import com.bimalghara.cleanarchitecture.domain.repository.AuthRepositorySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) : AuthRepositorySource {

    override suspend fun registerOrLogin(
        username: String,
        password: String
    ): ResourceWrapper<Long> {

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

    override fun getUserData(): Flow<List<AuthData>> {
        return localDataSource.getUserData()
    }

    override suspend fun getUserDataById(id: Long): AuthData? {
        return localDataSource.getUserDataById(id)
    }

    override suspend fun getLastSession(id: Long): Long {
        return localDataSource.getLastLoginSession(id = id)
    }
}