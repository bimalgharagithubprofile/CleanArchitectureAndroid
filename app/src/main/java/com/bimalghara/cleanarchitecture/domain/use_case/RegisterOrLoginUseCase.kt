package com.bimalghara.cleanarchitecture.domain.use_case

import com.bimalghara.cleanarchitecture.domain.repository.AuthRepositorySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RegisterOrLoginUseCase(private val authRepositorySource: AuthRepositorySource) {

    operator fun invoke(username: String, password: String):Flow<ResourceWrapper<Long>> = flow {
        emit(ResourceWrapper.Loading())

        emit(authRepositorySource.registerOrLogin(username, password))

    }.flowOn(Dispatchers.IO)
}