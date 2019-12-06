package com.ar4i.weather.presentation.cities.view

import ru.skillbranch.gameofthrones.presentation.base.IBaseView

interface ICitiesView : IBaseView {
    fun setCities(cities: List<String>)
    fun showWeatherScreenByCityName(cityName: String)
    fun showWeatherScreenByLocation(lat: String, lon: String)
}