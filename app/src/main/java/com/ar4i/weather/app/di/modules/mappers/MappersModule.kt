package com.ar4i.weather.app.di.modules.mappers

import com.ar4i.weather.app.di.modules.repositories.RepositoriesModule
import com.ar4i.weather.data.mappers.IWeatherMapper
import com.ar4i.weather.data.mappers.WeatherMapper

object MappersModule {
    private var weatherMapper: IWeatherMapper =
        WeatherMapper.also { it.setResourceRepository(RepositoriesModule.provideResourcesRepository()) }

    fun provideWeatherMapper(): IWeatherMapper = weatherMapper
}