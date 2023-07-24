package com.example.passwordmanager

class PasswordRepository(private val db:PasswordDatabase) {
    suspend fun insert(items: PasswordItems) = db.getPasswordDao().insert(items)
    suspend fun delete(items: PasswordItems) = db.getPasswordDao().delete(items)

    fun getAllItems() = db.getPasswordDao().getAllGroceryItems()
}