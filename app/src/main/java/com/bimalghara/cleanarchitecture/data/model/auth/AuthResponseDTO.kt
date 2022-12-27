package com.bimalghara.cleanarchitecture.data.model.auth

/**
 * Created by BimalGhara
 */
data class AuthResponseDTO(
    val id: String? = null, val firstName: String? = null, val lastName: String? = null,
    val streetName: String? = null, val buildingNumber: String? = null,
    val postalCode: String? = null, val state: String? = null,
    val country: String? = null, val email: String? = null
)
