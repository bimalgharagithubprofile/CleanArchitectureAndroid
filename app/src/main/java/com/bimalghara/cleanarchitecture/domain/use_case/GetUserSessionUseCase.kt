package com.bimalghara.cleanarchitecture.domain.use_case

import com.bimalghara.cleanarchitecture.domain.repository.AuthRepositorySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class GetUserSessionUseCase(private val ioDispatcher: CoroutineContext, private val authRepositorySource: AuthRepositorySource) {

    operator fun invoke():Flow<Long> = flow {
        //emit(ResourceWrapper.Loading())

        emit(authRepositorySource.getLastSession())

    }.flowOn(ioDispatcher)
}