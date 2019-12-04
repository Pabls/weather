package com.ar4i.weather.app.di.components

import com.ar4i.weather.presentation.cities.view.CitiesFragment
import com.ar4i.weather.presentation.weather.view.WeatherFragment

interface IApplicationComponent {
    fun inject(citiesFragment: CitiesFragment)
    fun inject(weatherFragment: WeatherFragment)
}