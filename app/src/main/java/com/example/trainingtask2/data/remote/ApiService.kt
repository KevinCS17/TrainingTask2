package com.example.trainingtask2.data.remote

import com.example.trainingtask2.data.model.CartoonModel
import com.example.trainingtask2.data.model.CartoonResultModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun fetchCartoonById(@Query("page_id") page_id : Int): CartoonModel
}