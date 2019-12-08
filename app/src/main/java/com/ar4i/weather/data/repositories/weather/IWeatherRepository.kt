package com.ar4i.weather.data.repositories.weather

import com.ar4i.weather.data.models.ApiError
import com.ar4i.weather.data.models.CityWeatherVm
import com.ar4i.weather.data.network.response.ApiResponse

interface IWeatherRepository {
    suspend fun getWeatherByCityName(
        cityName: String,
        numOfDays: Int? = null
    ) : Pair<CityWeatherVm?, ApiError?>
}