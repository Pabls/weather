package com.ar4i.weather.app.di.modules.mappers

import com.ar4i.weather.app.di.modules.repositories.RepositoriesModule
import com.ar4i.weather.data.mappers.ErrorHandler
import com.ar4i.weather.data.mappers.IErrorHandler
import com.ar4i.weather.data.mappers.IWeatherMapper
import com.ar4i.weather.data.mappers.WeatherMapper

object MappersModule {
    private var weatherMapper: IWeatherMapper =
        WeatherMapper.also { it.setResourceRepository(RepositoriesModule.provideResourcesRepository()) }

    private var errorHandler: IErrorHandler = ErrorHandler.also { it.setResourceRepository(RepositoriesModule.provideResourcesRepository()) }

    fun provideWeatherMapper(): IWeatherMapper = weatherMapper
    fun provideErrorHandler(): IErrorHandler = errorHandler
}