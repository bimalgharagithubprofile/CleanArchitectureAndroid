package com.bimalghara.cleanarchitecture.data.local

import android.content.Context
import com.bimalghara.cleanarchitecture.data.local.room.AuthDao
import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

class LocalDataImpl @Inject constructor(
    private val context: Context,
    private val authDao: AuthDao
) : LocalDataSource {

    override suspend fun saveUserData(authData: AuthData): Long {
        return authDao.addUser(authData)
    }

    override fun getUserData(): Flow<List<AuthData>> {
        return authDao.getUser()
    }

    override suspend fun getUserDataById(id: Long): AuthData? {
        return authDao.getUserById(id = id)
    }

    override suspend fun getLastLoginSession(id: Long): Long {
        return authDao.getLastTimestamp(id = id)
    }
}