package com.ar4i.weather.data.repositories.resources

interface IResourcesRepository {
    fun getStringById(id: Int) : String
}