package com.bimalghara.cleanarchitecture.data.local

import android.content.Context
import com.bimalghara.cleanarchitecture.data.model.auth.AuthData
import javax.inject.Inject

class LocalDataImpl @Inject constructor(private val context: Context) : LocalDataSource {

    override suspend fun saveUserData(authData: AuthData): Long {
        return 1111
    }

    override suspend fun getUserData(): AuthData {
        return AuthData(id="1111", "bimal", "ghara", "bimal@gmail.com")
    }

    override suspend fun getLastLoginSession(): Long {
        return 1672119625
    }
}