package com.example.trainingtask2.data.repository

import com.example.trainingtask2.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CartoonRepository @Inject constructor(private val api: ApiService) {
    fun getCartoon(cartoonId:Int)=flow{
        emit(api.fetchCartoonById(cartoonId))
    }.flowOn(Dispatchers.IO)
}