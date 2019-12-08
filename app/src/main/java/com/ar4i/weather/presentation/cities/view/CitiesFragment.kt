package com.ar4i.weather.presentation.cities.view

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.ar4i.weather.R
import com.ar4i.weather.presentation.base.view.BaseFragment
import com.ar4i.weather.presentation.cities.presenter.CitiesPresenter
import com.ar4i.weather.presentation.cities.view.adapter.CitiesAdapter
import com.ar4i.weather.presentation.main.IRouter

class CitiesFragment : BaseFragment(), ICitiesView {

    companion object {
        fun newInstance() = CitiesFragment()
    }

    private var etSearch: EditText? = null
    private var rvCities: RecyclerView? = null
    private var adapter: CitiesAdapter? = null
    private var vNoCities: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.fragment_cities

    override fun inject() {
        getComponent().inject(citiesFragment = this)
    }

    override fun setCities(cities: List<String>) {
        showCities(cities.isNotEmpty())
        adapter?.addAllAndNotify(cities)
    }

    override fun showWeatherScreenByCityName(cityName: String) {
        getRouter()?.showWeatherScreenByCityName(cityName)
    }

    override fun showWeatherScreenByLocation(lat: String, lon: String) {
        getRouter()?.showWeatherScreenByLocation(lat, lon)
    }

    private fun initView(view: View) {
        etSearch = view.findViewById(R.id.et_search)
        rvCities = view.findViewById(R.id.rv_cities)
        vNoCities = view.findViewById(R.id.v_no_cities)
        adapter = CitiesAdapter { name -> getCitiesPresenter()?.onCityClick(name) }
        rvCities?.adapter = adapter

        etSearch?.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                getCitiesPresenter()?.searchCity(etSearch?.text.toString())
                etSearch?.text = null
                etSearch?.clearFocus()

                val inputMethodManager =
                    activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                if (activity!!.currentFocus != null) {
                    inputMethodManager.hideSoftInputFromWindow(activity!!.currentFocus!!.windowToken, 0)
                }
            }
            false
        }
    }

    private fun getRouter(): IRouter? = if (activity is IRouter) activity as IRouter else null

    private fun getCitiesPresenter(): CitiesPresenter? {
        return if (getPresenter() != null) {
            getPresenter() as CitiesPresenter
        } else null
    }

    private fun showCities(show: Boolean) {
        if (show) {
            rvCities?.visibility = View.VISIBLE
            vNoCities?.visibility = View.GONE
        } else {
            rvCities?.visibility = View.GONE
            vNoCities?.visibility = View.VISIBLE
        }
    }
}