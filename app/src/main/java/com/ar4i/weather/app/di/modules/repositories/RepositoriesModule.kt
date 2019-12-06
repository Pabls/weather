package com.ar4i.weather.app.di.modules.repositories

import com.ar4i.weather.app.di.modules.app.AppModule
import com.ar4i.weather.app.di.modules.network.NetworkModule
import com.ar4i.weather.data.repositories.cities.CitiesRepository
import com.ar4i.weather.data.repositories.cities.ICitiesRepository
import com.ar4i.weather.data.repositories.resources.IResourcesRepository
import com.ar4i.weather.data.repositories.resources.ResourcesRepository
import com.ar4i.weather.data.repositories.weather.IWeatherRepository
import com.ar4i.weather.data.repositories.weather.WeatherRepository

object RepositoriesModule {
    private val resourcesRepository =
        ResourcesRepository.also { it.setContext(AppModule.provideContext()) }

    private val weatherRepository = WeatherRepository.also { it.setApi(NetworkModule.provideApi()) }

    private val citiesRepository = CitiesRepository

    fun provideResourcesRepository(): IResourcesRepository = resourcesRepository
    fun provideWeatherRepository(): IWeatherRepository = weatherRepository
    fun provideCitiesRepository(): ICitiesRepository = citiesRepository
}