package com.example.trainingtask2.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.trainingtask2.data.remote.ApiLogin
import com.example.trainingtask2.data.remote.ApiService
import com.example.trainingtask2.data.repository.CartoonDataSource
import com.example.trainingtask2.data.repository.CartoonRepository
import com.example.trainingtask2.data.repository.LoginRepository

class LoginViewModel @ViewModelInject constructor(private val loginRepository: LoginRepository):
    @androidx.hilt.lifecycle.ViewModelInject ViewModel(){

    fun login(username: String, password : String){
        Log.d("test123","halohalo123")
        loginRepository.login(username,password)
    }
}