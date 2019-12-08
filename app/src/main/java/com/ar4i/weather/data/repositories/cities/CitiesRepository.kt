package com.ar4i.weather.data.repositories.cities

import com.ar4i.weather.data.database.CitiesDao
import com.ar4i.weather.data.database.CityDto

object CitiesRepository : ICitiesRepository {

    private lateinit var citiesDao: CitiesDao

    fun setCitiesDao(citiesDao: CitiesDao) {
        this.citiesDao = citiesDao
    }

    override fun getCities(): List<String> = mutableListOf(
        "Moscow",
        "Perm",
        "Sochi",
        "Vladimir",
        "St.Petersburg",
        "Cyprus",
        "Barcelona",
        "Madrid",
        "London",
        "Dublin",
        "Paris",
        "Lion",
        "NY"
    )

    override fun saveCity(cityName: String) {
        //citiesDao.insertCity(CityDto(name = cityName))
    }

    override fun removeCity(cityName: String) {
        //citiesDao.deleteCityByName(cityName)
    }
}