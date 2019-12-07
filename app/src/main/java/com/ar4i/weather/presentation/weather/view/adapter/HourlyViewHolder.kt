package com.ar4i.weather.presentation.weather.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ar4i.weather.R
import com.ar4i.weather.data.models.HourlyVm
import com.ar4i.weather.presentation.base.view.BaseViewHolder
import com.squareup.picasso.Picasso

class HourlyViewHolder(view: View, private val onHourClick: (String) -> Unit) : BaseViewHolder<HourlyVm>(view) {

    private var clContainer: ConstraintLayout
    private var tvTime: TextView
    private var ivState: ImageView
    private var tvTemperature: TextView

    init {
        clContainer = view.findViewById(R.id.cl_container)
        tvTime = view.findViewById(R.id.tv_time)
        ivState = view.findViewById(R.id.iv_state)
        tvTemperature = view.findViewById(R.id.tv_temperature)
    }

    override fun bind(item: HourlyVm) {
        tvTime.text = item.time
        tvTemperature.text = item.temperature
        clContainer.setOnClickListener { onHourClick(item.time) }

        Picasso.get()
            .load(item.imageUrl)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(ivState)
    }
}