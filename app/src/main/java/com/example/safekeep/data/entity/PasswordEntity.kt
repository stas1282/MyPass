package com.example.safekeep.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class PasswordType { SITE, APP, PIN_CODE }

@Entity(tableName = "passwords")
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val username: String = "",
    val password: String,
    val url: String = "",
    val notes: String = "",
    val type: String = "SITE",
    val createdAt: Long = System.currentTimeMillis()
)
