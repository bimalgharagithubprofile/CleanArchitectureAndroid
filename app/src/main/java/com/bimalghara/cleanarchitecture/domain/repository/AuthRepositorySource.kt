package com.bimalghara.cleanarchitecture.domain.repository

import com.bimalghara.cleanarchitecture.data.model.auth.AuthData
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper

interface AuthRepositorySource {
    suspend fun registerOrLogin(firstName: String, lastName: String): ResourceWrapper<Long>
    suspend fun getUserData(): ResourceWrapper<AuthData>
    suspend fun getLastSession(): Long
}