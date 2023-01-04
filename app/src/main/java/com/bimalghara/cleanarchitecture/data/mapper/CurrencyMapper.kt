package com.bimalghara.cleanarchitecture.data.mapper

//DTO
data class CurrencyDTO(
    val code: String?,
    val compare: List<CompareDTO>?,
    val name: String?
)
data class CompareDTO(
    val name: String?=null,
    val rate: String?=null
)
//Model/Entity
data class Currency(
    val code: String,
    val compare: List<Compare>,
    val name: String
)
data class Compare(
    val name: String,
    val rate: String
)




fun CurrencyDTO.toDomain():Currency{
    return Currency(
        code = code?:"",
        compare = compare?.map { it.toDomain() }?: emptyList(),
        name=name?:""
    )
}

fun CompareDTO.toDomain():Compare{
    return Compare(
        name = name?:"",
        rate=rate?:""
    )
}