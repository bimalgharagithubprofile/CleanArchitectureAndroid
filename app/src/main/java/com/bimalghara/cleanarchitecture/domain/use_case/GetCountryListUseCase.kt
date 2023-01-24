package com.bimalghara.cleanarchitecture.domain.use_case

import com.bimalghara.cleanarchitecture.data.error.CustomException
import com.bimalghara.cleanarchitecture.domain.model.country.Country
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

/**
 * Created by BimalGhara
 */

class GetCountryListUseCase(private val ioDispatcher: CoroutineContext, private val countryRepositorySource: CountryRepositorySource)  {

    operator fun invoke():Flow<ResourceWrapper<List<Country>>> = flow {
        emit(ResourceWrapper.Loading())

        try {
            val result = countryRepositorySource.getCountryList()
            emit(ResourceWrapper.Success(data = result))
        }catch (e: CustomException){
            emit(ResourceWrapper.Error(e))
        }

    }.flowOn(ioDispatcher)


}