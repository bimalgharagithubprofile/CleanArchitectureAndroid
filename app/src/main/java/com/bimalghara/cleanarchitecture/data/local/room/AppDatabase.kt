package com.bimalghara.cleanarchitecture.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bimalghara.cleanarchitecture.domain.model.auth.AuthData

/**
 * Created by BimalGhara
 */

@Database(
    entities = [AuthData::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "clean_arch_app_db"
    }

    abstract val authDao: AuthDao
}