package com.ar4i.weather.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ar4i.weather.R
import com.ar4i.weather.presentation.cities.view.CitiesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            addFragment(CitiesFragment.newInstance())
        }
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
