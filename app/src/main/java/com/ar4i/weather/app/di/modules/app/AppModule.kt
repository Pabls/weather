package com.ar4i.weather.app.di.modules.app

import android.content.Context

object AppModule {
    private lateinit var context: Context

    fun setContext(context: Context) {
        this.context = context
    }

    fun provideContext(): Context = context
}