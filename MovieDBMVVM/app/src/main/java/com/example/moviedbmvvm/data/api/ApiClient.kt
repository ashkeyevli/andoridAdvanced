package com.example.moviedbmvvm.data.api

import android.content.SharedPreferences
import com.example.moviedbmvvm.utils.Constants.Companion.BASE_URL
import com.example.moviedbmvvm.utils.Constants.Companion.DEBUG
import com.example.moviedbmvvm.utils.Constants.Companion.REQUEST_TIMEOUT_DURATION
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    fun create(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    fun getOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        val logginInterceptor = HttpLoggingInterceptor()
        logginInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        return builder.addInterceptor(logginInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(authInterceptor)
            .build()
    }

    fun getAuthInterceptor(sharedPreferences: SharedPreferences): Interceptor {
        return Interceptor { chain ->
            val newRequest = chain.request()
                .newBuilder()
                .addHeader("Authorization", sharedPreferences.getString("token", "")!!)
                .build()

            chain.proceed(newRequest)
        }
    }
}