package com.ar4i.weather.app.di.components

import android.content.Context
import com.ar4i.weather.app.di.modules.presentation.PresentationModule
import com.ar4i.weather.presentation.cities.view.CitiesFragment

class ApplicationComponent(private val context: Context) : IApplicationComponent {

    private val presentationModule = PresentationModule(context)

    override fun inject(citiesFragment: CitiesFragment) {
        citiesFragment.setPresenter(presentationModule.provideCitiesPresenter())
    }
}