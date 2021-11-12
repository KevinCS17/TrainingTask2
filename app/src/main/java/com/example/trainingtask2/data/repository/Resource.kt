package com.example.trainingtask2.data.repository

import okhttp3.ResponseBody

sealed class Resource<out T> {
    class Success<out T>(val Value: T):Resource<T>()
    class Loading(val isLoading : Boolean, val progress:Int? = null, val message: String? = null): Resource<Nothing>()
    class Error(val isNetworkError:Boolean, val errorCode: Int?, val errorBody: ResponseBody?) : Resource<Nothing>()
}