package com.bimalghara.cleanarchitecture.data.mapper

import com.bimalghara.cleanarchitecture.data.model.CountryDTO
import com.bimalghara.cleanarchitecture.domain.model.Country

fun CountryDTO.toDomain(): Country {
    return Country(
        name=name?:"",
        url=url?:""
    )
}