package com.ar4i.weather.app.di.modules.repositories

import android.annotation.SuppressLint
import com.ar4i.weather.app.di.modules.app.AppModule
import com.ar4i.weather.app.di.modules.database.DatabaseModule
import com.ar4i.weather.app.di.modules.mappers.MappersModule
import com.ar4i.weather.app.di.modules.network.NetworkModule
import com.ar4i.weather.data.repositories.cities.CitiesRepository
import com.ar4i.weather.data.repositories.cities.ICitiesRepository
import com.ar4i.weather.data.repositories.location.ILocationRepository
import com.ar4i.weather.data.repositories.location.LocationRepository
import com.ar4i.weather.data.repositories.resources.IResourcesRepository
import com.ar4i.weather.data.repositories.resources.ResourcesRepository
import com.ar4i.weather.data.repositories.weather.IWeatherRepository
import com.ar4i.weather.data.repositories.weather.WeatherRepository

@SuppressLint("StaticFieldLeak")
object RepositoriesModule {
    private val resourcesRepository =
        ResourcesRepository.also { it.setContext(AppModule.provideContext()) }

    private val weatherRepository = WeatherRepository.also {
        it.setApi(NetworkModule.provideApi())
        it.setWeatherMapper(MappersModule.provideWeatherMapper())
        it.setErrorHandler(MappersModule.provideErrorHandler())
    }

    private val citiesRepository =
        CitiesRepository.also { it.setCitiesDao(DatabaseModule.provideCitiesDao()) }

    private val locationRepository = LocationRepository.also { it.setContext(AppModule.provideContext()) }

    fun provideResourcesRepository(): IResourcesRepository = resourcesRepository
    fun provideWeatherRepository(): IWeatherRepository = weatherRepository
    fun provideCitiesRepository(): ICitiesRepository = citiesRepository
    fun provideLocationRepository(): ILocationRepository = locationRepository
}