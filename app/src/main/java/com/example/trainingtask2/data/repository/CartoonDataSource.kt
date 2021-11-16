package com.example.trainingtask2.data.repository

import android.util.Log
import androidx.paging.PagingSource
import com.example.trainingtask2.data.model.CartoonModel
import com.example.trainingtask2.data.model.LoginModel
import com.example.trainingtask2.data.model.Result
import com.example.trainingtask2.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

//class CartoonDataSource @Inject constructor(private val api : ApiService): PagingSource<Int, Result>(){
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
//        val nextPageNumber = params.key?:1
//        return try{
//            val response = api.fetchCartoonById(nextPageNumber).results
//            val prevKey = if(nextPageNumber == 1) null else nextPageNumber - 1
//            val nextKey = if(response.isEmpty()){
//                null
//            }else{
//                nextPageNumber + (params.loadSize/20)
//            }
//            LoadResult.Page(
//                data = response,
//                prevKey = prevKey,
//                nextKey = nextKey
//            )
//
//        } catch (e: Exception){
//            LoadResult.Error(e)
//        }
//    }
//}

class CartoonDataSource @Inject constructor(private val cartoonRepository: CartoonRepository):
    PagingSource<Int, Result>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val nextPageNumber = params.key?:1
        return try{
            val response = ArrayList<Result>()

            cartoonRepository.getAllCartoon(nextPageNumber).collect {
                when(it){
                    is Resource.Success -> response.addAll(it.Value.results)
                    else -> Unit
                }
            }
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