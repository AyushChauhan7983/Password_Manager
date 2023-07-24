package com.example.passwordmanager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PasswordItems::class], version = 1)
abstract class PasswordDatabase : RoomDatabase() {
    abstract fun getPasswordDao() : PasswordDao

    companion object{
        @Volatile
        private var instance : PasswordDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PasswordDatabase::class.java,
                "Password.db"
            ).build()

    }
}