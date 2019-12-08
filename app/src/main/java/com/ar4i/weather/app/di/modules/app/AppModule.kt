package com.ar4i.weather.app.di.modules.app

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object AppModule {
    private lateinit var context: Context

    fun setContext(context: Context) {
        this.context = context
    }

    fun provideContext(): Context = context
}