package com.ar4i.weather.presentation.cities.view

import ru.skillbranch.gameofthrones.presentation.base.IBaseView

interface ICitiesView : IBaseView {
    fun setCities(cities: List<String>)
    fun showWeatherScreenByCityName(cityName: String, isFavorite: Boolean)
    fun clearEditText()
    fun requestLocationPermissions()
    fun showCities(show: Boolean)
    fun checkCities()
    fun setPermissionsResult(arePermissionsObtained: Boolean)
}