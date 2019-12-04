package com.ar4i.weather.app

import android.app.Application
import com.ar4i.weather.app.di.components.ApplicationComponent
import com.ar4i.weather.app.di.components.IApplicationComponent

class WeatherApp: Application() {

    companion object {
        lateinit var applicationComponent: IApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = ApplicationComponent(this)
    }
}