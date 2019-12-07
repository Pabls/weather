package com.ar4i.weather.presentation.weather.presenter

import com.ar4i.weather.data.models.ApiError
import com.ar4i.weather.data.models.CityWeatherVm
import com.ar4i.weather.data.models.DayWeatherVm
import com.ar4i.weather.data.models.HourlyVm
import com.ar4i.weather.data.repositories.resources.IResourcesRepository
import com.ar4i.weather.data.repositories.weather.IWeatherRepository
import com.ar4i.weather.presentation.base.BasePresenter
import com.ar4i.weather.presentation.weather.view.IWeatherView

class WeatherPresenter(
    private val weatherRepository: IWeatherRepository,
    private val resourceRepository: IResourcesRepository
) : BasePresenter<IWeatherView>() {

    private var cityVm: CityWeatherVm? = null

    override fun attachView(view: IWeatherView?) {
        super.attachView(view)
        getView()?.showLoading()
        getWeather()
    }

    fun onDayClick(date: String) {
        val dayVm = getDayWeatherVmByDate(date)
        if (dayVm != null) {
            if (cityVm != null && dayVm.date == cityVm!!.date) {
                showCurrentWeather()
            } else {
                showWeatherFromHourlyVm(dayVm.hourly.first())
            }
            getView()?.setHourly(dayVm.hourly)
            getView()?.setDate(dayVm.date)
        }
    }

    fun onHourClick(time: String, date: String) {
        val dayVm = getDayWeatherVmByDate(date)
        if (dayVm != null) {
            val hourlyVm = dayVm.hourly.find { it.time == time }
            if (hourlyVm != null) {
                showWeatherFromHourlyVm(hourlyVm)
            }
        }
    }

    private fun getWeather() {
        if (getView()?.getCityName() != null)
            weatherRepository.getWeatherByCityName(
                cityName = getView()?.getCityName()!!,
                result = { handleResponse(it) })
        else {
            val location = getView()?.getLocation()
            if (location?.first != null)
                weatherRepository.getWeatherByLocation(
                    lat = location.first!!,
                    lon = location.second!!,
                    result = { handleResponse(it) })
        }
    }

    private fun handleResponse(response: Pair<CityWeatherVm?, ApiError?>) {
        if (response.second != null) {
            getView()?.showError(message = response.second!!.message)
            getView()?.showNotFoundError()
        } else if (response.first != null) {
            //val weatherVm = response.first!!
            this.cityVm = response.first!!
            getView()?.setDays(cityVm!!.weather)
            getView()?.setHourly(cityVm!!.weather.first().hourly)
            getView()?.setCityName(cityVm!!.cityName)
            showCurrentWeather()
        }
        getView()?.hideLoading()
    }

    private fun showCurrentWeather() {
        if (cityVm != null) {
            getView()?.setDate(cityVm!!.date)

            setData(
                description = cityVm!!.description,
                pressure = cityVm!!.pressure,
                windSpeed = cityVm!!.windSpeed,
                humidity = cityVm!!.humidity,
                imageUrl = cityVm!!.imageUrl,
                temperature = cityVm!!.temperature
            )
        }
    }

    private fun showWeatherFromHourlyVm(hourlyVm: HourlyVm) {
        setData(
            description = hourlyVm.description,
            pressure = hourlyVm.pressure,
            windSpeed = hourlyVm.windSpeed,
            humidity = hourlyVm.humidity,
            imageUrl = hourlyVm.imageUrl,
            temperature = hourlyVm.temperature
        )
    }

    private fun setData(
        description: String,
        temperature: String,
        pressure: String,
        windSpeed: String,
        humidity: String,
        imageUrl: String
    ) {
        getView()?.setDescription(description)
        getView()?.setTemperature(temperature)
        getView()?.setPressure(pressure)
        getView()?.setWindSpeed(windSpeed)
        getView()?.setHumidity(humidity)
        getView()?.setCurrentCondition(imageUrl)
    }

    private fun getDayWeatherVmByDate(date: String): DayWeatherVm? {
        return if (cityVm != null && cityVm!!.weather.isNotEmpty()) {
            cityVm!!.weather.find { it.date == date }
        } else {
            null
        }
    }

//    fun getHourlyByLocalTime(): HourlyVm? {
//        val currentHour =
//            SimpleDateFormat("HH", Locale.getDefault()).also { it.timeZone = TimeZone.getDefault() }.format(Date())
//        return hourly.find {
//            val hh = it.time.substringBefore(":")
//            hh == currentHour || hh.toInt() > currentHour.toInt() - 2
//        }
//    }
}