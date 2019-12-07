package com.ar4i.weather.data.repositories.resources

import android.content.Context
import android.graphics.drawable.Drawable

object ResourcesRepository : IResourcesRepository {

    private lateinit var context: Context

    fun setContext(context: Context) {
        this.context = context
    }

    override fun getStringById(id: Int): String = context.getString(id)

    override fun getDrawableById(id: Int): Drawable? = context.getDrawable(id)
}