package com.ar4i.weather.app.di.modules.database

import androidx.room.Room
import com.ar4i.weather.app.di.modules.app.AppModule
import com.ar4i.weather.data.database.CitiesDao
import com.ar4i.weather.data.database.WeatherDatabase

object DatabaseModule {
    private var database: WeatherDatabase

    init {
        database =
            Room.databaseBuilder(AppModule.provideContext(), WeatherDatabase::class.java, WeatherDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    fun provideCitiesDao(): CitiesDao = database.getCitiesDao()
}