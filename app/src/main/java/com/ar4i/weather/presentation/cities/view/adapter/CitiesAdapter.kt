package com.ar4i.weather.presentation.cities.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ar4i.weather.R
import com.ar4i.weather.presentation.base.view.BaseAdapter

class CitiesAdapter(private val onCityClick: (String) -> Unit) : BaseAdapter<String, CitiesViewHolder>() {

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItemByPosition(position: Int): String? = if (items.size > position) items[position] else null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CitiesViewHolder(onCityClick, view)
    }
}