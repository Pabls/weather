package com.ar4i.weather.app.di.modules.converters

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object ConvertersModule {
    private val gson = GsonBuilder().create()
    fun provideGson(): Gson = gson
}