package com.bimalghara.cleanarchitecture.domain.repository

import com.bimalghara.cleanarchitecture.domain.model.Country
import com.bimalghara.cleanarchitecture.utils.ResourceWrapper


/**
 * Created by BimalGhara
 */

interface CountryRepositorySource {

    suspend fun getCountryList(): ResourceWrapper<List<Country>>

}