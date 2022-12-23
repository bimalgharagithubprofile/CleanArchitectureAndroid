package com.bimalghara.cleanarchitecture.data.network.retrofit.service

import com.bimalghara.cleanarchitecture.data.model.CountryDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by BimalGhara
 */

interface ApiServiceCountries {


    @GET("countries.json")
    suspend fun getCountryList():List<CountryDTO>



}