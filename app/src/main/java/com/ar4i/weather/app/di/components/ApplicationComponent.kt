package com.ar4i.weather.app.di.components

import android.content.Context
import com.ar4i.weather.app.di.modules.presentation.PresentationModule
import com.ar4i.weather.presentation.cities.view.CitiesFragment
import com.ar4i.weather.presentation.weather.view.WeatherFragment

class ApplicationComponent(private val context: Context) : IApplicationComponent {

    private val presentationModule = PresentationModule(context)

    override fun inject(citiesFragment: CitiesFragment) {
        citiesFragment.setPresenter(presentationModule.provideCitiesPresenter())
    }

    override fun inject(weatherFragment: WeatherFragment) {
        weatherFragment.setPresenter(presentationModule.provideWeatherPresenter())
    }
}