package com.example.trainingtask2.data.model

import androidx.room.Entity

@Entity
data class LoginModel(
    val data: Data,
    val message: String,
    val status: Boolean,
    val token: String
)