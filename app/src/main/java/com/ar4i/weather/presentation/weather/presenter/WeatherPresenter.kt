package com.ar4i.weather.presentation.weather.presenter

import com.ar4i.weather.data.network.response.ApiResponce
import com.ar4i.weather.data.repositories.resources.IResourcesRepository
import com.ar4i.weather.data.repositories.weather.IWeatherRepository
import com.ar4i.weather.presentation.base.BasePresenter
import com.ar4i.weather.presentation.weather.view.IWeatherView

class WeatherPresenter(
    private val weatherRepository: IWeatherRepository,
    private val resourceRepository: IResourcesRepository
) : BasePresenter<IWeatherView>() {

    override fun attachView(view: IWeatherView?) {
        super.attachView(view)
        getView()?.showLoading()
        weatherRepository.getWeatherByCityName(cityName = "Moscow", result = {
            handleResponce(it)
        })
    }

    fun handleResponce(responce: ApiResponce?) {
        getView()?.hideLoading()
    }
}