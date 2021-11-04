package com.example.trainingtask2.data.model

import com.google.gson.annotations.SerializedName

data class CartoonResultModel(
    val page: Int,
    @field:SerializedName("results")
    val movieModels: List<CartoonModel>,
    @field:SerializedName("total_pages")
    val totalPages: Int,
    @field:SerializedName("total_results")
    val totalResults: Int
)