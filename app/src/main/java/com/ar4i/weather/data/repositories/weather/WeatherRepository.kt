package com.ar4i.weather.data.repositories.weather

import com.ar4i.weather.data.network.Api
import com.ar4i.weather.data.network.response.ApiResponce
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WeatherRepository : IWeatherRepository {

    private const val DEFAULT_NUM_OF_DAYS = 5
    private lateinit var api: Api

    fun setApi(api: Api) {
        this.api = api
    }

    override fun getWeatherByCityName(
        cityName: String,
        numOfDays: Int?,
        result: (ApiResponce?) -> Unit
    ) {
        getWeather(
            query = cityName,
            numOfDays = getNumOfDays(numOfDays),
            result = result
        )
    }

    override fun getWeatherByLocation(
        lat: String,
        lon: String,
        numOfDays: Int?,
        result: (ApiResponce?) -> Unit
    ) {
        getWeather(
            query = "${lat},${lon}",
            numOfDays = getNumOfDays(numOfDays),
            result = result
        )
    }

    private fun getWeather(
        query: String,
        numOfDays: Int,
        result: (ApiResponce?) -> Unit
    ) {
        api?.getWeather(query = query, numOfDays = numOfDays)
            .enqueue(object : Callback<ApiResponce> {

                override fun onFailure(call: Call<ApiResponce>, t: Throwable) {
                    result.invoke(null)
                }

                override fun onResponse(call: Call<ApiResponce>, response: Response<ApiResponce>) {
                    if (response.body() != null) {
                        result.invoke(response.body())
                    }
                }
            })
    }

    private fun getNumOfDays(numOfDays: Int?): Int = numOfDays ?: DEFAULT_NUM_OF_DAYS
}