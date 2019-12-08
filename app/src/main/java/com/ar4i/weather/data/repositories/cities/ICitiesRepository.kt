package com.ar4i.weather.data.repositories.cities

interface ICitiesRepository {
    fun getCities(): List<String>
    fun saveCity(cityName: String)
    fun removeCity(cityName: String)
}