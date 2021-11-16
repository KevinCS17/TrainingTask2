package com.example.trainingtask2.data.repository

import android.util.Log
import com.example.trainingtask2.data.model.LoginModel
import com.example.trainingtask2.data.remote.ApiLogin
import com.example.trainingtask2.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: ApiLogin) {
    fun login(username: String, password: String) = flow<Resource<LoginModel>>{
        try{
          emit(Resource.Success(api.login(username,password)))
        } catch (t : Throwable){
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