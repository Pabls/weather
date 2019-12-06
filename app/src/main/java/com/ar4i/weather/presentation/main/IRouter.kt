package com.ar4i.weather.presentation.main

interface IRouter {
    fun showWeatherScreenByCityName(cityName: String)
    fun showWeatherScreenByLocation(lat: String, lon: String)
}