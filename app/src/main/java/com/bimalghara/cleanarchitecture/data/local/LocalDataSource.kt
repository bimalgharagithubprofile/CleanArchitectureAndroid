package com.bimalghara.cleanarchitecture.data.local

import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

interface LocalDataSource {
    suspend fun saveUserData(authData: AuthData): Long
    suspend fun saveUserId(id: Long)
    suspend fun getUserId(): Long

    //can't be suspending because it's Flow
    fun getUserData(): Flow<List<AuthData>>

    suspend fun getUserDataById(id: Long): AuthData?

    suspend fun getLastLoginSession(id: Long): Long
}