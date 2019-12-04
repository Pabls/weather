package com.ar4i.weather.presentation.cities.view

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.ar4i.weather.R
import com.ar4i.weather.presentation.base.view.BaseFragment
import com.ar4i.weather.presentation.cities.view.adapter.CitiesAdapter

class CitiesFragment : BaseFragment(), ICitiesView {

    companion object {
        fun newInstance() = CitiesFragment()
    }

    private var etSearch: EditText? = null
    private var rvCities: RecyclerView? = null
    private var adapter: CitiesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.fragment_cities

    override fun inject() {
        getComponent().inject(citiesFragment = this)
    }

    override fun setCities(cities: List<String>) {
        adapter?.addAllAndNotify(cities)
    }

    private fun initView(view: View) {
        etSearch = view.findViewById(R.id.et_search)
        rvCities = view.findViewById(R.id.rv_cities)
        adapter = CitiesAdapter()
        rvCities?.adapter = adapter
    }
}