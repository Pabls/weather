package com.ar4i.weather.presentation.cities.view.adapter

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ar4i.weather.R
import com.ar4i.weather.presentation.base.view.BaseViewHolder

class CitiesViewHolder(private val onCityClick : (String) -> Unit, private val view: View) :
    BaseViewHolder<String>(view) {

    private var clContainer: ConstraintLayout
    private var tvCityName: TextView

    init {
        clContainer = view.findViewById(R.id.cl_container)
        tvCityName = view.findViewById(R.id.tv_city_name)
    }

    override fun bind(item: String) {
        tvCityName.text = item
        clContainer.setOnClickListener { onCityClick(item)}
    }
}