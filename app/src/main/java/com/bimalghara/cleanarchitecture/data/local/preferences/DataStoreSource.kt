package com.bimalghara.cleanarchitecture.data.local.preferences

import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

interface DataStoreSource {
    suspend fun saveString(key: String, value: String)

    suspend fun getString(key: String): String?

}