package com.ar4i.weather.presentation.weather.view

import com.ar4i.weather.R
import com.ar4i.weather.presentation.base.view.BaseFragment

class WeatherFragment : BaseFragment(), IWeatherView {

    override fun getLayoutId(): Int = R.layout.fragment_weather

    override fun inject() { getComponent().inject(this) }
}