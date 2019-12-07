package com.ar4i.weather.presentation.weather.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ar4i.weather.R
import com.ar4i.weather.data.models.HourlyVm
import com.ar4i.weather.presentation.base.view.BaseAdapter

class HourlyAdapter(private val onHourClick : (String) -> Unit) : BaseAdapter<HourlyVm, HourlyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hourly, parent, false)
        return HourlyViewHolder(view, onHourClick)
    }
}