package com.bimalghara.cleanarchitecture.data.network

import com.bimalghara.cleanarchitecture.data.model.country.CountryDTO
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper

/**
 * Created by BimalGhara
 */

interface RemoteDataSource {
    suspend fun requestCountries(): ResourceWrapper<List<CountryDTO>>
}
