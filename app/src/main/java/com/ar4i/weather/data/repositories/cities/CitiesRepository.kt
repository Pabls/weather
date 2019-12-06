package com.ar4i.weather.data.repositories.cities

object CitiesRepository : ICitiesRepository {


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
}