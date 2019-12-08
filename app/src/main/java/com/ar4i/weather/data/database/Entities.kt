package com.ar4i.weather.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityDto(
    @PrimaryKey
    val name: String
)