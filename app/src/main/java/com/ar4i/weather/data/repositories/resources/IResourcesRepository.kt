package com.ar4i.weather.data.repositories.resources

import android.graphics.drawable.Drawable

interface IResourcesRepository {
    fun getStringById(id: Int) : String
    fun getDrawableById(id: Int) : Drawable?
}