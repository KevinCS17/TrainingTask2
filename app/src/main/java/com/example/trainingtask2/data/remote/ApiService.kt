package com.example.trainingtask2.data.remote

import com.example.trainingtask2.data.model.CartoonModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun fetchCartoonById(@Query("page") page_id : Int): CartoonModel
}