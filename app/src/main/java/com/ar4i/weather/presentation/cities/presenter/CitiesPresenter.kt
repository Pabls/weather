package com.ar4i.weather.presentation.cities.presenter

import com.ar4i.weather.presentation.base.BasePresenter
import com.ar4i.weather.presentation.cities.view.ICitiesView

class CitiesPresenter : BasePresenter<ICitiesView>() {

    override fun attachView(view: ICitiesView?) {
        super.attachView(view)
        val cities = mutableListOf<String>("Moscow", "Perm", "Sochi", "Vladimir", "St.Petersburg", "Cyprus", "Barcelona", "Madrid", "London", "Dublin", "Paris", "Lion", "NY")
        getView()?.setCities(cities)
    }
}