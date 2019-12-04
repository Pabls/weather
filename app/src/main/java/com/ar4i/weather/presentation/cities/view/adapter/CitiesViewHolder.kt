package com.ar4i.weather.presentation.cities.view.adapter

import android.view.View
import android.widget.TextView
import com.ar4i.weather.R
import com.ar4i.weather.presentation.base.view.BaseViewHolder

class CitiesViewHolder(private val view: View) : BaseViewHolder<String>(view) {

    private var tvCityName: TextView

    init {
        tvCityName = view.findViewById(R.id.tv_city_name)
    }

    override fun bind(item: String) {
        tvCityName.text = item
    }
}