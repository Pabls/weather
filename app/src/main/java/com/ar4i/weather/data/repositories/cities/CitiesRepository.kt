package com.ar4i.weather.data.repositories.cities

import com.ar4i.weather.data.database.CitiesDao
import com.ar4i.weather.data.database.CityDto

object CitiesRepository : ICitiesRepository {

    private lateinit var citiesDao: CitiesDao

    fun setCitiesDao(citiesDao: CitiesDao) {
        this.citiesDao = citiesDao
    }

    override suspend fun getCities(): List<String> {
        val cities = citiesDao.getCities()
        return cities.map { it.name }
    }

    override suspend fun saveCity(cityName: String) {
        citiesDao.insertCity(CityDto(name = cityName))
    }

    override suspend fun removeCity(cityName: String) {
        citiesDao.deleteCityByName(cityName)
    }
}