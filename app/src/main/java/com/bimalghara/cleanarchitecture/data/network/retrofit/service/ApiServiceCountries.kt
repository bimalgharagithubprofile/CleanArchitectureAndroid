package com.bimalghara.cleanarchitecture.data.network.retrofit.service

import com.bimalghara.cleanarchitecture.data.model.country.CountryDTO
import retrofit2.http.GET

/**
 * Created by BimalGhara
 */

interface ApiServiceCountries {


    @GET("countries.json")
    suspend fun getCountryList():List<CountryDTO>



}