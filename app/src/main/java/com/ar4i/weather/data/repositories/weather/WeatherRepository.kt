package com.ar4i.weather.data.repositories.weather

import com.ar4i.weather.data.mappers.IErrorHandler
import com.ar4i.weather.data.mappers.IWeatherMapper
import com.ar4i.weather.data.models.ApiError
import com.ar4i.weather.data.models.CityWeatherVm
import com.ar4i.weather.data.network.Api

object WeatherRepository : IWeatherRepository {

    private const val DEFAULT_NUM_OF_DAYS = 5
    private lateinit var api: Api
    private lateinit var weatherMapper: IWeatherMapper
    private lateinit var errorHandler: IErrorHandler

    fun setApi(api: Api) {
        this.api = api
    }

    fun setWeatherMapper(weatherMapper: IWeatherMapper) {
        this.weatherMapper = weatherMapper
    }

    fun setErrorHandler(errorHandler: IErrorHandler) {
        this.errorHandler = errorHandler
    }

    override suspend fun getWeatherByCityName(
        cityName: String,
        numOfDays: Int?
    ): Pair<CityWeatherVm?, ApiError?> {
        return try {
            val response = api.getWeather(query = cityName, numOfDays = getNumOfDays(numOfDays))
            weatherMapper.mapApiResponseToVm(response)
        } catch (ex: Exception) {
            null to ApiError(message = errorHandler.getErrorMessage(ex))
        }
    }

    private fun getNumOfDays(numOfDays: Int?): Int = numOfDays ?: DEFAULT_NUM_OF_DAYS
}