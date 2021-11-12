package com.example.trainingtask2.data.remote

import com.example.trainingtask2.data.model.CartoonModel
import com.example.trainingtask2.data.model.LoginModel
import retrofit2.http.*

interface ApiService {
    @GET("character")
    suspend fun fetchCartoonById(@Query("page") page_id : Int): CartoonModel
}