package com.bimalghara.cleanarchitecture.data.mapper

import com.bimalghara.cleanarchitecture.data.model.country.CountryDTO
import com.bimalghara.cleanarchitecture.domain.model.country.Country

fun CountryDTO.toDomain(): Country {
    return Country(
        name=name?:"",
        url=url?:""
    )
}