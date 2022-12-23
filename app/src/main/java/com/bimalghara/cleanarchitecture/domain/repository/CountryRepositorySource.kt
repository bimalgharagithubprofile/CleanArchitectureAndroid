package com.bimalghara.cleanarchitecture.domain.repository

import com.bimalghara.cleanarchitecture.domain.model.Country


/**
 * Created by BimalGhara
 */

interface CountryRepositorySource {

    suspend fun getCountryList():List<Country>

}