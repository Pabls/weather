package com.ar4i.weather.data.repositories.cities

interface ICitiesRepository {
    suspend fun getCities(): List<String>
    suspend fun saveCity(cityName: String)
    suspend fun removeCity(cityName: String)
}