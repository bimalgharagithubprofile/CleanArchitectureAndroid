package com.bimalghara.cleanarchitecture.data.repository

import com.bimalghara.cleanarchitecture.data.error.CustomException
import com.bimalghara.cleanarchitecture.data.error.ERROR_AUTH_FAILED
import com.bimalghara.cleanarchitecture.data.local.LocalDataSource
import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData
import com.bimalghara.cleanarchitecture.domain.repository.AuthRepositorySource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    AuthRepositorySource {

    override suspend fun registerOrLogin(
        username: String,
        password: String
    ): Long {

        val name = username.split("@")[0]
        val auth = AuthData(
            firstName = name,//mocked dummy data
            lastName = name,//mocked dummy data
            email = username
        )
        val response = localDataSource.saveUserData(auth)
        return if (response > 0) {
            localDataSource.saveUserId(response)
            response
        } else {
            throw CustomException(cause = ERROR_AUTH_FAILED)
        }
    }

    override fun getUserData(): Flow<List<AuthData>> {
        return localDataSource.getUserData()
    }

    override suspend fun getUserDataById(id: Long): AuthData? {
        return localDataSource.getUserDataById(id)
    }

    override suspend fun getLastSession(): Long {
        val userId = localDataSource.getUserId()
        return if(userId==-1L) userId else localDataSource.getLastLoginSession(id = userId)
    }
}