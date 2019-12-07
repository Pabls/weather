package com.ar4i.weather.presentation.weather.view.adapter

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ar4i.weather.R
import com.ar4i.weather.data.models.DayWeatherVm
import com.ar4i.weather.presentation.base.view.BaseViewHolder

class DayViewHolder(view: View, private val onDayClick : (String) -> Unit) : BaseViewHolder<DayWeatherVm>(view) {

    private var clContainer: ConstraintLayout
    private var tvDate: TextView
    private var tvHumidity: TextView
    private var tvTemperature: TextView
    private var tvPressure: TextView
    private var tvWindSpeed: TextView

    init {
        clContainer = view.findViewById(R.id.cl_container)
        tvDate = view.findViewById(R.id.tv_date)
        tvHumidity = view.findViewById(R.id.tv_humidity)
        tvTemperature = view.findViewById(R.id.tv_temperature)
        tvPressure = view.findViewById(R.id.tv_atmosphere_pressure)
        tvWindSpeed = view.findViewById(R.id.tv_wind_speed)
    }

    override fun bind(item: DayWeatherVm) {
        tvDate.text = item.date
        tvHumidity.text = item.humidity
        tvTemperature.text = item.avgTemp
        tvPressure.text = item.pressure
        tvWindSpeed.text = item.windSpeed
        clContainer.setOnClickListener { onDayClick(item.date) }
    }
}