package com.example.birdview.model.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object HwaHaeApiCreator {
    val BASE_URL = "https://6uqljnm1pb.execute-api.ap-northeast-2.amazonaws.com/prod"

    private fun defaultRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return defaultRetrofit()
            .create(service)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .retryOnConnectionFailure(true)
            .build()
    }

}