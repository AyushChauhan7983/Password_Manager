package com.example.passwordmanager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PasswordViewModalFactory(private val repository: PasswordRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        return PasswordViewModal(repository) as T
    }
}