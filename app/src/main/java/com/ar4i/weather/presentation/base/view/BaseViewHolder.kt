package com.ar4i.weather.presentation.base.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<V>(private val view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: V)
}