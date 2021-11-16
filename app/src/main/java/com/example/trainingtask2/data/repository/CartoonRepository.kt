package com.example.trainingtask2.data.repository

import com.example.trainingtask2.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class CartoonRepository @Inject constructor(private val api: ApiService) {
    fun getAllCartoon(page: Int) = flow{
        try{
            emit(Resource.Success(api.fetchCartoonById(page)))
        }catch (t:Throwable){
            when(t){
                is HttpException ->{
                    emit(Resource.Error(false,t.code(),t.response()?.errorBody()))
                }
                else ->{
                    emit(Resource.Error(true,null,null))
                }
            }
        }
    }.flowOn(IO)
}