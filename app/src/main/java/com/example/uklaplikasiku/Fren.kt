package com.example.uklaplikasiku

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fren(
    @PrimaryKey(autoGenerate = true)
    val temanId: Int? = null,
    val nama : String,
    val email : String,
    val username: String,
    val password: String
)