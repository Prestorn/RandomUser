package com.example.randomuser.di

import com.example.randomuser.data.repository.UserRepositoryImpl
import com.example.randomuser.data.retrofit.controllers.UserController
import com.example.randomuser.data.retrofit.service.ApiService
import com.example.randomuser.data.retrofit.service.RetrofitService
import com.example.randomuser.data.storage.provideAppDB
import com.example.randomuser.domain.repository.UserRepository
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECTION_URL = "https://randomuser.me/"
private const val TIMEOUT_SECONDS: Long = 5
val dataModule = module {
    factory<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(CONNECTION_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<UserController> {
        val retrofit: Retrofit = get()
        retrofit.create(UserController::class.java)
    }
    single<ApiService> { RetrofitService(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    singleOf(::provideAppDB)
}