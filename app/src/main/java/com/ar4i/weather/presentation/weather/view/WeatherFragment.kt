package com.ar4i.weather.presentation.weather.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ar4i.weather.R
import com.ar4i.weather.presentation.base.view.BaseFragment

class WeatherFragment : BaseFragment(), IWeatherView {

    companion object {
        const val EXTRA_CITY_NAME = "EXTRA_CITY_NAME"
        const val EXTRA_LAT = "EXTRA_LAT"
        const val EXTRA_LON = "EXTRA_LAT"

        fun newInstance(lat: String, lon: String) = WeatherFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_LAT, lat)
                putString(EXTRA_LON, lon)
            }
        }

        fun newInstance(cityName: String) = WeatherFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_CITY_NAME, cityName)
            }
        }
    }

    private var tvCityName: TextView? = null
    private var tvTemperatureLabel: TextView? = null
    private var tvAtmospherePressure: TextView? = null
    private var tvTemperature: TextView? = null
    private var tvWindSpeed: TextView? = null
    private var tvHumidity: TextView? = null
    private var rvTime: RecyclerView? = null
    private var rvDays: RecyclerView? = null

    private var cityName: String? = null
    private var lat: String? = null
    private var lon: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            cityName = it.getString(EXTRA_CITY_NAME)
            lat = it.getString(EXTRA_LAT)
            lon = it.getString(EXTRA_LON)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.fragment_weather

    override fun inject() {
        getComponent().inject(this)
    }

    private fun initView(view: View) {
        tvCityName = view.findViewById(R.id.tv_city_name)
        tvTemperatureLabel = view.findViewById(R.id.tv_temperature_label)
        tvAtmospherePressure = view.findViewById(R.id.tv_atmosphere_pressure)
        tvTemperature = view.findViewById(R.id.tv_temperature)
        tvWindSpeed = view.findViewById(R.id.tv_wind_speed)
        tvHumidity = view.findViewById(R.id.tv_humidity)
        rvTime = view.findViewById(R.id.rv_time)
        rvDays = view.findViewById(R.id.rv_days)
    }
}