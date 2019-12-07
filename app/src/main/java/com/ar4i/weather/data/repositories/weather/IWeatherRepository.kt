package com.ar4i.weather.data.repositories.weather

import com.ar4i.weather.data.models.ApiError
import com.ar4i.weather.data.models.CityWeatherVm
import com.ar4i.weather.data.network.response.ApiResponse

interface IWeatherRepository {
    fun getWeatherByCityName(
        cityName: String,
        numOfDays: Int? = null,
        result: (Pair<CityWeatherVm?, ApiError?>) -> Unit
    )

    fun getWeatherByLocation(
        lat: String,
        lon: String,
        numOfDays: Int? = null,
        result: (Pair<CityWeatherVm?, ApiError?>) -> Unit
    )
}