package com.ar4i.weather.presentation.base.view

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<V, VH : BaseViewHolder<V>> : RecyclerView.Adapter<VH>() {

    protected var items = mutableListOf<V>()

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    protected fun getItem(position: Int): V {
        return items[position]
    }

    fun addAllAndNotify(items: List<V>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }
}