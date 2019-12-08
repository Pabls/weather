package com.ar4i.weather.presentation.main

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ar4i.weather.R
import com.ar4i.weather.presentation.cities.view.CitiesFragment
import com.ar4i.weather.presentation.cities.view.ICitiesView
import com.ar4i.weather.presentation.weather.view.WeatherFragment

class MainActivity : AppCompatActivity(), IRouter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            addFragment(CitiesFragment.newInstance())
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CitiesFragment.PERMISSION_LOCATION) {
            val unacceptedPermissions = grantResults.filter { it != PackageManager.PERMISSION_GRANTED }
            if (unacceptedPermissions.isNotEmpty()) {
                setPermissionsResult(false)
            } else {
                setPermissionsResult(true)
            }
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 1) {
            val fragment = findCitiesFragment()
            if (fragment != null) {
                (fragment as CitiesFragment).checkCities()
            }
        }
        super.onBackPressed()
    }

    override fun showWeatherScreenByCityName(cityName: String, isFavorite: Boolean) {
        addFragmentToBackStack(WeatherFragment.newInstance(cityName = cityName, isFavorite = isFavorite))
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

    private fun setPermissionsResult(arePermissionsObtained: Boolean) {
        val fragment = findCitiesFragment()
        if (fragment != null) {
            (fragment as CitiesFragment).setPermissionsResult(arePermissionsObtained)
        }
    }

    private fun findCitiesFragment(): Fragment? = supportFragmentManager.fragments.find { it is ICitiesView }
}
