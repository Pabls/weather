package com.ar4i.weather.app.di.modules.network

import com.ar4i.weather.BuildConfig
import com.ar4i.weather.app.di.modules.converters.ConvertersModule
import com.ar4i.weather.data.network.Api
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private var client: OkHttpClient
    private var retrofit: Retrofit
    private var api: Api

    init {
        client = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(ConvertersModule.provideGson()))
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

        api = retrofit.create(Api::class.java)
    }

    fun provideApi(): Api = api
}