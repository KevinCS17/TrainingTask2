package com.example.trainingtask2.di

import android.content.Context
import com.example.trainingtask2.MainActivity
import com.example.trainingtask2.constants.ConstValue.BASE_URL
import com.example.trainingtask2.constants.ConstValue.BASE_URL_LOGIN
import com.example.trainingtask2.data.model.LoginModel
import com.example.trainingtask2.data.remote.ApiLogin
import com.example.trainingtask2.data.remote.ApiService
import com.example.trainingtask2.data.repository.CartoonDataSource
import com.example.trainingtask2.data.repository.CartoonRepository
import com.example.trainingtask2.data.repository.LoginRepository
import com.example.trainingtask2.session.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    @Provides
    @Singleton
    fun getRetrofit(): ApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()
        .create(ApiService::class.java)

    @Provides
    fun provideCartoonRepository(apiService: ApiService) = CartoonRepository(apiService)

    @Provides
    @Singleton
    fun getRetrofitLogin(): ApiLogin = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL_LOGIN)
        .build()
        .create(ApiLogin::class.java)

    @Provides
    fun provideRepository(apiLogin:ApiLogin) = LoginRepository(apiLogin)

    @Provides
    fun provideSession(@ApplicationContext context: Context) = SessionManager().setContext(context)
}