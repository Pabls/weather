package com.ar4i.weather.data.mappers

import com.ar4i.weather.R
import com.ar4i.weather.data.models.ApiError
import com.ar4i.weather.data.models.CityWeatherVm
import com.ar4i.weather.data.models.DayWeatherVm
import com.ar4i.weather.data.models.HourlyVm
import com.ar4i.weather.data.network.response.*
import com.ar4i.weather.data.repositories.resources.IResourcesRepository
import java.text.SimpleDateFormat
import java.util.*

object WeatherMapper : IWeatherMapper {

    private lateinit var resourceRepository: IResourcesRepository

    fun setResourceRepository(resourceRepository: IResourcesRepository) {
        this.resourceRepository = resourceRepository
    }

    override fun mapApiResponseToVm(response: ApiResponse?): Pair<CityWeatherVm?, ApiError?> {
        return if (response == null) {
            getError()
        } else {
            if (!response.data.error.isNullOrEmpty()) {
                null to ApiError(message = response.data.error.first().message)
            } else {
                val currentCondition = response.data.currentCondition?.first()
                val requestInfo = response.data.request?.first()
                val weather = response.data.weather
                if (currentCondition != null && requestInfo != null && weather != null) {
                    mapCityWeatherVm(currentCondition, requestInfo, weather) to null
                } else {
                    getError()
                }
            }
        }
    }

    private fun mapCityWeatherVm(
        currentCondition: CurrentCondition,
        requestInfo: RequestInfo,
        weather: List<Weather>
    ): CityWeatherVm {
        val degree = resourceRepository.getStringById(R.string.fragment_weather_degree)
        val windSpeed = resourceRepository.getStringById(R.string.fragment_weather_wind_speed)
        val humidityPercent = resourceRepository.getStringById(R.string.fragment_weather_humidity_percent)
        val pressureMba = resourceRepository.getStringById(R.string.fragment_weather_pressure_mbar)

        return CityWeatherVm(
            cityName = requestInfo.query,
            date = getCurrentDate(),
            time = resourceRepository.getStringById(R.string.fragment_weather_current_time),
            temperature = currentCondition.temp.toString() + degree,
            description = currentCondition.descriptions.first().value,
            imageUrl = currentCondition.weatherIconUrl.first().value,
            pressure = currentCondition.pressure.toString() + pressureMba,
            windSpeed = currentCondition.windSpeed.toString() + windSpeed,
            humidity = currentCondition.humidity.toString() + humidityPercent,
            weather = weather.map { mapDayWeatherVm(it, pressureMba, windSpeed, humidityPercent, degree) }
        )
    }

    private fun mapDayWeatherVm(
        weather: Weather,
        pressureMba: String,
        windSpeed: String,
        humidityPercent: String,
        degree: String
    ): DayWeatherVm =
        DayWeatherVm(
            date = weather.date,
            avgTemp = weather.avgTemp.toString() + degree,
            pressure = weather.hourly.first().pressure.toString() + pressureMba,
            windSpeed = weather.hourly.first().windSpeed.toString() + windSpeed,
            humidity = weather.hourly.first().humidity.toString() + humidityPercent,
            hourly = weather.hourly.map { mapHourlyVm(it, degree) }
        )

    private fun mapHourlyVm(hourly: Hourly, degree: String): HourlyVm =
        HourlyVm(
            time = formatTime(hourly.time),
            imageUrl = hourly.weatherIconUrl.first().value,
            temperature = hourly.temp.toString() + degree,
            humidity = hourly.humidity.toString(),
            windSpeed = hourly.windSpeed.toString(),
            pressure = hourly.pressure.toString(),
            description = hourly.descriptions.first().value
        )

    private fun getError(): Pair<CityWeatherVm?, ApiError?> =
        null to ApiError(message = resourceRepository.getStringById(R.string.error_try_later))

    private fun formatTime(time: String): String = if (time.length > 1) {
        val minutes = time.substring(time.length - 2)
        val hours = time.substring(0, time.length - 2)
        "${hours}:${minutes}"
    } else {
        "${time}:00"
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date())
    }
}