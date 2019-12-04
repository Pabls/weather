package com.ar4i.weather.app.di.components

import com.ar4i.weather.presentation.cities.view.CitiesFragment

interface IApplicationComponent {
    fun inject(citiesFragment: CitiesFragment)
}