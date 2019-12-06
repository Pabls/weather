package com.ar4i.weather.data.repositories.weather

import com.ar4i.weather.data.network.response.ApiResponce

interface IWeatherRepository {
    fun getWeatherByCityName(
        cityName: String,
        numOfDays: Int? = null,
        result: (ApiResponce?) -> Unit
    )

    fun getWeatherByLocation(
        lat: String,
        lon: String,
        numOfDays: Int? = null,
        result: (ApiResponce?) -> Unit
    )
}