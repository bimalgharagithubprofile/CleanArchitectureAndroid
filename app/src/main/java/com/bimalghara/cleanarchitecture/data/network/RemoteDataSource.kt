package com.bimalghara.cleanarchitecture.data.network

import com.bimalghara.cleanarchitecture.data.model.CountryDTO
import com.bimalghara.cleanarchitecture.domain.model.Country
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper

/**
 * Created by BimalGhara
 */

internal interface RemoteDataSource {
    suspend fun requestCountries(): ResourceWrapper<List<CountryDTO>>
}
