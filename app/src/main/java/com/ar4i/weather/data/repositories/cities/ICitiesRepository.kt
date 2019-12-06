package com.ar4i.weather.data.repositories.cities

interface ICitiesRepository {
    fun getCities(): List<String>
}