package com.ar4i.weather.data.mappers

import com.ar4i.weather.data.models.ApiError
import com.ar4i.weather.data.models.CityWeatherVm
import com.ar4i.weather.data.network.response.ApiResponse

interface IWeatherMapper {
    fun mapApiResponseToVm(response: ApiResponse?) : Pair<CityWeatherVm?, ApiError?>
}