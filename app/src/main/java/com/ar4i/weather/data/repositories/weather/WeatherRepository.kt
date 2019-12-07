package com.ar4i.weather.data.repositories.weather

import com.ar4i.weather.data.mappers.IWeatherMapper
import com.ar4i.weather.data.models.ApiError
import com.ar4i.weather.data.models.CityWeatherVm
import com.ar4i.weather.data.network.Api
import com.ar4i.weather.data.network.response.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WeatherRepository : IWeatherRepository {

    private const val DEFAULT_NUM_OF_DAYS = 5
    private lateinit var api: Api
    private lateinit var weatherMapper: IWeatherMapper

    fun setApi(api: Api) {
        this.api = api
    }

    fun setWeatherMapper(weatherMapper: IWeatherMapper) {
        this.weatherMapper = weatherMapper
    }

    override fun getWeatherByCityName(
        cityName: String,
        numOfDays: Int?,
        result: (Pair<CityWeatherVm?, ApiError?>) -> Unit
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
        result: (Pair<CityWeatherVm?, ApiError?>) -> Unit
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
        result: (Pair<CityWeatherVm?, ApiError?>) -> Unit
    ) {
        api.getWeather(query = query, numOfDays = numOfDays)
            .enqueue(object : Callback<ApiResponse> {

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    result.invoke(null to ApiError(message = t.message ?: ""))
                }

                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    result.invoke(weatherMapper.mapApiResponseToVm(response.body()))
                }
            })
    }

    private fun getNumOfDays(numOfDays: Int?): Int = numOfDays ?: DEFAULT_NUM_OF_DAYS
}