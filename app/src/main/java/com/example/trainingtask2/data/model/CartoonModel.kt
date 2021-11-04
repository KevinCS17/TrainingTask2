package com.example.trainingtask2.data.model

import androidx.room.Entity

@Entity
data class  CartoonModel(
    val info: Info,
    val results: List<Result>
)