package com.ar4i.weather.presentation.weather.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ar4i.weather.R
import com.ar4i.weather.presentation.base.view.BaseFragment

class WeatherFragment : BaseFragment(), IWeatherView {

    private var tvCityName: TextView? = null
    private var tvTemperatureLabel: TextView? = null
    private var tvAtmospherePressure: TextView? = null
    private var tvTemperature: TextView? = null
    private var tvWindSpeed: TextView? = null
    private var tvHumidity: TextView? = null
    private var rvTime: RecyclerView? = null
    private var rvDays: RecyclerView? = null

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