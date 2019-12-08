package com.ar4i.weather.presentation.weather.view

import com.ar4i.weather.data.models.DayWeatherVm
import com.ar4i.weather.data.models.HourlyVm
import ru.skillbranch.gameofthrones.presentation.base.IBaseView

interface IWeatherView : IBaseView {
    fun getCityName(): String?
    fun isFavoriteCity(): Boolean?
    fun setDays(days: List<DayWeatherVm>)
    fun setHourly(hourly: List<HourlyVm>)
    fun setCityName(cityName: String)
    fun setDate(date: String)
    fun setTime(time: String)
    fun setCurrentCondition(url: String)
    fun setDescription(description: String)
    fun setTemperature(temp: String)
    fun setPressure(pressure: String)
    fun setWindSpeed(speed: String)
    fun setHumidity(humidity: String)
    fun showNotFoundError()
    fun disableFab()
    fun showFab(show: Boolean)
    fun showFavoriteImg()
}