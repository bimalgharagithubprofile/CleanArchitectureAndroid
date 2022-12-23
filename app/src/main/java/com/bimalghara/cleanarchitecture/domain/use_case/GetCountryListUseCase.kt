package com.bimalghara.cleanarchitecture.domain.use_case

import com.bimalghara.cleanarchitecture.domain.model.Country
import com.bimalghara.cleanarchitecture.domain.repository.CountryRepositorySource
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

/**
 * Created by BimalGhara
 */

class GetCountryListUseCase(private val countryRepositorySource: CountryRepositorySource)  {

    operator fun invoke():Flow<ResourceWrapper<List<Country>>> = flow {
        emit(ResourceWrapper.Loading())
        try{
            emit(ResourceWrapper.Success(data = countryRepositorySource.getCountryList()))

        }catch (e:Exception){
            emit(ResourceWrapper.Error(errorCode = -3)) //TODO pending
        }
    }.flowOn(Dispatchers.IO)


}