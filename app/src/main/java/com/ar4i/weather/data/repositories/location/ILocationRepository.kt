package com.ar4i.weather.data.repositories.location

interface ILocationRepository {
    fun hasPermissions(): Boolean
    fun getCityNameByLocation(): String?
}