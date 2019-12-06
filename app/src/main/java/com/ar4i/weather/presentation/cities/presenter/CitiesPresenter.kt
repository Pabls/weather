package com.ar4i.weather.presentation.cities.presenter

import com.ar4i.weather.data.repositories.cities.ICitiesRepository
import com.ar4i.weather.data.repositories.resources.IResourcesRepository
import com.ar4i.weather.data.repositories.weather.IWeatherRepository
import com.ar4i.weather.presentation.base.BasePresenter
import com.ar4i.weather.presentation.cities.view.ICitiesView

class CitiesPresenter(
    private val citiesRepository: ICitiesRepository,
    private val weatherRepository: IWeatherRepository,
    private val resourceRepository: IResourcesRepository
) : BasePresenter<ICitiesView>() {

    override fun attachView(view: ICitiesView?) {
        super.attachView(view)
        val cities = citiesRepository.getCities()
        getView()?.setCities(cities)
    }

    fun onCityClick(cityName: String) {
        getView()?.showWeatherScreenByCityName(cityName)
    }

}