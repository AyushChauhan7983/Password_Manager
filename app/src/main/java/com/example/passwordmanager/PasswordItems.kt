package com.example.passwordmanager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Password Manager")
data class PasswordItems(
@ColumnInfo(name = "Name")
var itemName: String,

@ColumnInfo(name = "Password")
var itemPassword: String,

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
