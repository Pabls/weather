package com.ar4i.weather.data.models


data class CityWeatherVm(
    val cityName: String,
    val date: String,
    val temperature: String,
    val description: String,
    val imageUrl: String,
    val pressure: String,
    val windSpeed: String,
    val humidity: String,
    val weather: List<DayWeatherVm>
)

data class DayWeatherVm(
    val date: String,
    val avgTemp: String,
    val pressure: String,
    val windSpeed: String,
    val humidity: String,
    val hourly: List<HourlyVm>
)

data class HourlyVm(
    val time: String,
    val imageUrl: String,
    val temperature: String,
    val description: String,
    val pressure: String,
    val windSpeed: String,
    val humidity: String
)

data class ApiError(
    val message: String
)