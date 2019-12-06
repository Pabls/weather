package com.ar4i.weather.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ar4i.weather.R
import com.ar4i.weather.presentation.cities.view.CitiesFragment
import com.ar4i.weather.presentation.weather.view.WeatherFragment

class MainActivity : AppCompatActivity(), IRouter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            addFragment(CitiesFragment.newInstance())
        }
    }

    override fun showWeatherScreenByCityName(cityName: String) {
        addFragmentToBackStack(WeatherFragment.newInstance(cityName = cityName))
    }

    override fun showWeatherScreenByLocation(lat: String, lon: String) {
        addFragmentToBackStack(WeatherFragment.newInstance(lat = lat, lon = lon))
    }

    private fun addFragment(fragment: Fragment) {
        getTransaction(fragment).commit()
    }

    private fun addFragmentToBackStack(fragment: Fragment) {
        getTransaction(fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun getTransaction(fragment: Fragment) = supportFragmentManager.beginTransaction()
        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
        .add(R.id.fl_container, fragment)
}
