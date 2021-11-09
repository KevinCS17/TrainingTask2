package com.example.trainingtask2.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.trainingtask2.data.remote.ApiService
import com.example.trainingtask2.data.repository.CartoonDataSource

class MainViewModel @ViewModelInject constructor(private val api:ApiService):
    @androidx.hilt.lifecycle.ViewModelInject ViewModel(){

        private var pagingSource: CartoonDataSource? = null

    var flow = Pager(PagingConfig(pageSize = 20)){
        CartoonDataSource(api).also{
            pagingSource = it
        }
    }.flow.cachedIn(viewModelScope)

    fun clearData()= pagingSource?.invalidate()
}