package com.ar4i.weather.presentation.weather.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ar4i.weather.R
import com.ar4i.weather.data.models.DayWeatherVm
import com.ar4i.weather.presentation.base.view.BaseAdapter

class DaysAdapter(private val onDayClick: (String) -> Unit) : BaseAdapter<DayWeatherVm, DayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return DayViewHolder(view, onDayClick)
    }
}