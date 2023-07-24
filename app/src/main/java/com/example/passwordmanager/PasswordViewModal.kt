package com.example.passwordmanager

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PasswordViewModal(private val repository: PasswordRepository) : ViewModel() {
    fun insert(items: PasswordItems) = GlobalScope.launch {
        repository.insert(items)
    }
    fun delete(items: PasswordItems) = GlobalScope.launch {
        repository.delete(items)
    }
    fun getAllGroceryItems() = repository.getAllItems()
}