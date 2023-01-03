package com.bimalghara.cleanarchitecture.domain.use_case

import com.bimalghara.cleanarchitecture.data.error.*
import com.bimalghara.cleanarchitecture.domain.repository.AuthRepositorySource
import com.bimalghara.cleanarchitecture.utils.RegexUtils
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RegisterOrLoginUseCase(private val authRepositorySource: AuthRepositorySource) {

    operator fun invoke(username: String, password: String):Flow<ResourceWrapper<Long>> = flow {
        emit(ResourceWrapper.Loading())

        val isUsernameValid = RegexUtils.isValidEmail(username.trim())
        val isPassWordValid = password.trim().length > 4

        if (isUsernameValid && !isPassWordValid) {
            emit(ResourceWrapper.Error(errorDetails = ErrorDetails(code = ERROR_PASS_WORD_ERROR)))
        } else if (!isUsernameValid && isPassWordValid) {
            emit(ResourceWrapper.Error(errorDetails = ErrorDetails(code = ERROR_USER_NAME_ERROR)))
        } else if (!isUsernameValid && !isPassWordValid) {
            emit(ResourceWrapper.Error(errorDetails = ErrorDetails(code = ERROR_CHECK_YOUR_FIELDS)))
        } else {
            //emit(authRepositorySource.registerOrLogin(username, password))

            try{
                val result = authRepositorySource.registerOrLogin(username, password)
                emit(ResourceWrapper.Success(data = result))
            }catch (e: CustomException){
                emit(ResourceWrapper.Error(errorDetails = ErrorDetails(code = e.message!!.toInt())))
            }

        }

    }.flowOn(Dispatchers.IO)
}