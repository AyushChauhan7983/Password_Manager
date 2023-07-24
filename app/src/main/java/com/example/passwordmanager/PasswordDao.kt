package com.example.passwordmanager

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

    @Dao
    interface PasswordDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(item: PasswordItems)

        @Delete
        fun delete(item: PasswordItems)

        @Query("SELECT * FROM `password manager`")
        fun getAllGroceryItems(): LiveData<List<PasswordItems>>
    }
