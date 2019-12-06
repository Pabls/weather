package com.ar4i.weather.app

import android.app.Application
import com.ar4i.weather.app.di.components.ApplicationComponent
import com.ar4i.weather.app.di.components.IApplicationComponent
import com.facebook.stetho.Stetho

class WeatherApp: Application() {

    companion object {
        lateinit var applicationComponent: IApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initStetho()
        applicationComponent = ApplicationComponent(this)
    }

    private fun initStetho() {
        val initializerBuilder = Stetho.newInitializerBuilder(this)
        initializerBuilder.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        initializerBuilder.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        val initializer = initializerBuilder.build()
        Stetho.initialize(initializer)
    }
}