package com.bimalghara.cleanarchitecture.data.local

import com.bimalghara.cleanarchitecture.data.model.auth.AuthData

/**
 * Created by BimalGhara
 */

interface LocalDataSource {
    suspend fun saveUserData(authData: AuthData): Long
    suspend fun getUserData(): AuthData

    suspend fun getLastLoginSession(): Long
}