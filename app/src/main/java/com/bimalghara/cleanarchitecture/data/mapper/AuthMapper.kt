package com.bimalghara.cleanarchitecture.data.mapper

import com.bimalghara.cleanarchitecture.data.model.auth.AuthRequestDTO
import com.bimalghara.cleanarchitecture.data.model.auth.AuthResponseDTO
import com.bimalghara.cleanarchitecture.domain.model.auth.AuthRequest
import com.bimalghara.cleanarchitecture.domain.model.auth.AuthResponse


fun AuthRequest.toData() : AuthRequestDTO {
    return AuthRequestDTO(
        email = email,
        password = password
    )
}

fun AuthResponseDTO.toDomain() : AuthResponse {
    return AuthResponse(
        id = id?:"",
        firstName = firstName?:"",
        lastName = lastName?:"",
        email = email?:""
    )
}