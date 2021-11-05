package com.example.trainingtask2.data.repository

import android.util.Log
import androidx.paging.PagingSource
import com.example.trainingtask2.data.model.CartoonModel
import com.example.trainingtask2.data.model.Result
import com.example.trainingtask2.data.remote.ApiService
import java.lang.Exception
import javax.inject.Inject

class CartoonDataSource @Inject constructor(private val api : ApiService):
    PagingSource<Int, Result>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val nextPageNumber = params.key?:1
        return try{
            val response = api.fetchCartoonById(nextPageNumber).results
            val prevKey = if(nextPageNumber == 1) null else nextPageNumber - 1
            val nextKey = if(response.isEmpty()){
                null
            }else{
                nextPageNumber + (params.loadSize/20)
            }
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}