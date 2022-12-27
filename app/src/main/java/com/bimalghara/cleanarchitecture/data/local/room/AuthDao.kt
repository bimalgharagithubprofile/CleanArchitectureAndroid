package com.bimalghara.cleanarchitecture.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData
import kotlinx.coroutines.flow.Flow

/**
 * Created by BimalGhara
 */

@Dao
interface AuthDao {

    //can't be suspending because it's Flow
    @Query("SELECT * FROM AuthData")
    fun getUser(): Flow<List<AuthData>>

    //nullable return because id may not exists
    @Query("SELECT * FROM AuthData WHERE id = :id")
    suspend fun getUserById(id:Long): AuthData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(authData: AuthData): Long

    @Delete
    suspend fun deleteUser(authData: AuthData): Int

    @Query("SELECT updated_at FROM AuthData WHERE id = :id")
    suspend fun getLastTimestamp(id:Long): Long
}