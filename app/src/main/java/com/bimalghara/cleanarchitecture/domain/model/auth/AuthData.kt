package com.bimalghara.cleanarchitecture.domain.model.auth

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by BimalGhara
 */
@Entity
data class AuthData(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val updated_at: Long = System.currentTimeMillis()

)
