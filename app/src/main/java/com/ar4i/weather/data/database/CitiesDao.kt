package com.ar4i.weather.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityDto)

    @Query("SELECT * FROM cities;")
    suspend fun getCities(): List<CityDto>

    @Query("DELETE FROM cities WHERE name=:cityName;")
    suspend fun deleteCityByName(cityName: String)
}