package com.ar4i.weather.presentation.cities.presenter

import com.ar4i.weather.R
import com.ar4i.weather.data.repositories.cities.ICitiesRepository
import com.ar4i.weather.data.repositories.location.ILocationRepository
import com.ar4i.weather.data.repositories.resources.IResourcesRepository
import com.ar4i.weather.data.repositories.weather.IWeatherRepository
import com.ar4i.weather.presentation.base.BasePresenter
import com.ar4i.weather.presentation.cities.view.ICitiesView
import kotlinx.coroutines.*

class CitiesPresenter(
    private val citiesRepository: ICitiesRepository,
    private val weatherRepository: IWeatherRepository,
    private val resourceRepository: IResourcesRepository,
    private val locationRepository: ILocationRepository
) : BasePresenter<ICitiesView>() {

    private var cities = listOf<String>()
    private var getCitiesJob: Job? = null
    private var removeCitiyJob: Job? = null

    override fun attachView(view: ICitiesView?) {
        super.attachView(view)
        getCitiesJob = getCitiesAndCheckPermissions()
    }

    private fun getCitiesAndCheckPermissions() = GlobalScope.launch(Dispatchers.Main) {
        cities = withContext(Dispatchers.IO) { citiesRepository.getCities() }
        if (cities.isEmpty()) {
            tryGetLocation()
        } else {
            setCities(cities)
        }
    }

    override fun detachView() {
        getCitiesJob?.cancel()
        removeCitiyJob?.cancel()
        super.detachView()
    }

    fun onCityClick(cityName: String) {
        getView()?.showWeatherScreenByCityName(cityName, isFavorite = true)
    }

    fun searchCity(cityName: String) {
        getView()?.clearEditText()
        if (cityName.isNotEmpty()) {
            getView()?.showWeatherScreenByCityName(cityName, isFavorite = isFavoriteCity(cityName))
        }
    }

    fun tryGetLocation() {
        if (locationRepository.hasPermissions()) {
            getLocation()
        } else {
            getView()?.requestLocationPermissions()
        }
    }

    fun handlePermissionsResult(arePermissionsObtained: Boolean) {
        if (!arePermissionsObtained) {
            getView()?.showCities(false)
        } else {
            getLocation()
        }
    }

    fun refreshCities() {
        getCitiesJob = getCities()
    }

    fun tryRemoveCity(cityName: String?) {
        if (cityName != null)
            removeCitiyJob = removeCity(cityName)
    }

    private fun getCities() = GlobalScope.launch(Dispatchers.Main) {
        val cities = withContext(Dispatchers.IO) { citiesRepository.getCities() }
        if (cities.isNotEmpty()) {
            setCities(cities)
        } else {
            getView()?.showCities(false)
        }
    }

    private fun removeCity(cityName: String) = GlobalScope.launch(Dispatchers.IO) {
        citiesRepository.removeCity(cityName)
    }

    private fun setCities(cities: List<String>) {
        getView()?.setCities(cities)
        getView()?.showCities(true)
    }

    private fun getLocation() {
        val cityName = locationRepository.getCityNameByLocation()
        if (cityName != null) {
            getView()?.showWeatherScreenByCityName(cityName, isFavorite = isFavoriteCity(cityName))
        } else {
            getView()?.showError(resourceRepository.getStringById(R.string.fragment_cities_geolocation_disabled))
        }
    }

    private fun isFavoriteCity(cityName: String): Boolean = cities.contains(cityName)
}