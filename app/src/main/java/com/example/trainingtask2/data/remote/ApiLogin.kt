package com.example.trainingtask2.data.remote

import com.example.trainingtask2.data.model.LoginModel
import retrofit2.http.*

interface ApiLogin {
    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "X-API-KEY: 454041184B0240FBA3AACD15A1F7A8BB"
    )
    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") username:String, @Field("password") password:String): LoginModel
}