package com.bimalghara.cleanarchitecture.data.local

import android.content.Context
import android.util.Log
import com.bimalghara.cleanarchitecture.data.local.database.AuthDao
import com.bimalghara.cleanarchitecture.data.local.preferences.DataStoreSource
import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData
import com.bimalghara.cleanarchitecture.utils.DS_KEY_USER_ID
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by BimalGhara
 */

class LocalDataImpl @Inject constructor(
    private val context: Context,
    private val dataStore: DataStoreSource,
    private val authDao: AuthDao
) : LocalDataSource {

    override suspend fun saveUserData(authData: AuthData): Long {
        return authDao.addUser(authData)
    }

    override suspend fun saveUserId(id: Long) {
        dataStore.saveString(DS_KEY_USER_ID, id.toString())
    }

    override suspend fun getUserId(): Long {
        val result = dataStore.getString(DS_KEY_USER_ID)
        return result?.toLong() ?: -1
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