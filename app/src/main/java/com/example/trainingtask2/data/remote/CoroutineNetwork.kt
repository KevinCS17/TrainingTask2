//package com.example.trainingtask2.data.remote
//
//import com.example.trainingtask2.data.model.Cartoon
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//import retrofit2.http.Path
//
//class CoroutineNetwork{
//
//}
//
//private val service: Network by lazy {
//    val okHttpClient = OkHttpClient.Builder()
////        .addInterceptor(DummyNetworkInterceptor())
//        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
//        .build()
//
//    val retrofit = Retrofit.Builder()
//        .baseUrl("https://rickandmortyapi.com/api/")
//        .client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    retrofit.create(Network::class.java)
//}
//
//interface Network{
//    @GET("character?page={page_id}")
//    suspend fun fetchCartoonById(@Path("page_id")page_id: String): Cartoon
//}