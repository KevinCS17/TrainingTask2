package com.example.trainingtask2.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.trainingtask2.data.model.LoginModel
import com.example.trainingtask2.data.remote.ApiLogin
import com.example.trainingtask2.data.remote.ApiService
import com.example.trainingtask2.data.repository.CartoonDataSource
import com.example.trainingtask2.data.repository.CartoonRepository
import com.example.trainingtask2.data.repository.LoginRepository
import com.example.trainingtask2.data.repository.Resource
import kotlinx.coroutines.flow.Flow

class LoginViewModel @ViewModelInject constructor(private val loginRepository: LoginRepository):
    @androidx.hilt.lifecycle.ViewModelInject ViewModel(){

    //method1
//    fun login(username: String, password: String): Flow<Resource<LoginModel>> {
//        return loginRepository.login(username, password)
//    }
    //method2
    val login = MediatorLiveData<Resource<LoginModel>>()
    fun login(username: String, password : String){
        login.addSource(loginRepository.login(username, password).asLiveData()){
            login.value = it
        }
    }
}