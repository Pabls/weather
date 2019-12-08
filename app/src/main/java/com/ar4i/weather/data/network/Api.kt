package com.ar4i.weather.data.network

import com.ar4i.weather.BuildConfig
import com.ar4i.weather.data.network.response.ApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    companion object {
        const val URL_PREFIX = "/premium/v1/weather.ashx/"
        const val KEY = "key"
        const val Q = "q"
        const val FORMAT = "format"
        const val NUM_OF_DAYS = "num_of_days"
        const val JSON_FORMAT = "json"
    }

    @GET(URL_PREFIX)
    suspend fun getWeather(
        @Query(KEY) key: String = BuildConfig.API_KEY,
        @Query(Q) query: String,
        @Query(FORMAT) format: String = JSON_FORMAT,
        @Query(NUM_OF_DAYS) numOfDays: Int
    ): ApiResponse
}