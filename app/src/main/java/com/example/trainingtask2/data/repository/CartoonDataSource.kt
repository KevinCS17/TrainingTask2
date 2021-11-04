package com.example.trainingtask2.data.repository

import androidx.paging.PagingSource
import com.example.trainingtask2.data.model.CartoonModel
import com.example.trainingtask2.data.remote.ApiService
import java.lang.Exception
import javax.inject.Inject

class CartoonDataSource @Inject constructor(private val api : ApiService):
    PagingSource<Int, CartoonModel>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CartoonModel> {
        return try{
            val nextPageNumber = params.key?:1
            val response = api.fetchCartoonById(1)
            val responseData = mutableListOf<CartoonModel>()
            val data= response.movieModels?: emptyList()
            responseData.addAll(data)

            val prevKey = if(nextPageNumber == 1) null else nextPageNumber - 1

            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = nextPageNumber.plus(1)

            )

        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}