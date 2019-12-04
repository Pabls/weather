package com.ar4i.weather.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ar4i.weather.R
import com.ar4i.weather.presentation.base.view.BaseFragment
import com.ar4i.weather.presentation.cities.view.CitiesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setFragment(CitiesFragment.newInstance())
        }
    }

    private fun setFragment(fragment: BaseFragment?) {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                .add(R.id.fl_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
