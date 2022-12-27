package com.bimalghara.cleanarchitecture.domain.use_case

import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData
import com.bimalghara.cleanarchitecture.domain.repository.AuthRepositorySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAllRegisteredUsersUseCase(
    private val authRepositorySource: AuthRepositorySource
) {

    operator fun invoke() : Flow<List<AuthData>> {
        //emit(ResourceWrapper.Loading())

        //emit(ResourceWrapper.Success(data = authRepositorySource.getUserData()))

        return authRepositorySource.getUserData()
    }
}