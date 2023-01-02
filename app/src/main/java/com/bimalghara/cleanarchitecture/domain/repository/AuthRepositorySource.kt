package com.bimalghara.cleanarchitecture.domain.repository

import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow

interface AuthRepositorySource {

    suspend fun registerOrLogin(username: String, password: String): ResourceWrapper<Long>

    //can't be suspending because it's Flow
    fun getUserData(): Flow<List<AuthData>>

    suspend fun getUserDataById(id: Long): AuthData?

    suspend fun getLastSession(): Long
}