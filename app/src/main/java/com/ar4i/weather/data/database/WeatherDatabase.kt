package com.ar4i.weather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ar4i.weather.BuildConfig

@Database(
    entities = arrayOf(
        CityDto::class
    ), version = WeatherDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "${BuildConfig.APPLICATION_ID}.database"
        const val DATABASE_VERSION = 1
    }

    abstract fun getCitiesDao(): CitiesDao
}