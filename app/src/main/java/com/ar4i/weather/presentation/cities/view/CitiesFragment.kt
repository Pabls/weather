package com.ar4i.weather.presentation.cities.view

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.ar4i.weather.R
import com.ar4i.weather.presentation.base.view.BaseFragment
import com.ar4i.weather.presentation.cities.presenter.CitiesPresenter
import com.ar4i.weather.presentation.cities.view.adapter.CitiesAdapter
import com.ar4i.weather.presentation.main.IRouter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.ItemTouchHelper


class CitiesFragment : BaseFragment(), ICitiesView {

    companion object {
        const val PERMISSION_LOCATION = 1
        fun newInstance() = CitiesFragment()
    }

    private var etSearch: EditText? = null
    private var rvCities: RecyclerView? = null
    private var adapter: CitiesAdapter? = null
    private var vNoCities: View? = null
    private var btnGetLocation: Button? = null

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

    override fun showWeatherScreenByCityName(cityName: String, isFavorite: Boolean) {
        getRouter()?.showWeatherScreenByCityName(cityName, isFavorite)
    }

    override fun clearEditText() {
        etSearch?.text = null
        etSearch?.clearFocus()

        val inputMethodManager =
            activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (activity!!.currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(activity!!.currentFocus!!.windowToken, 0)
        }
    }

    override fun showCities(show: Boolean) {
        if (show) {
            rvCities?.visibility = View.VISIBLE
            vNoCities?.visibility = View.GONE
        } else {
            rvCities?.visibility = View.GONE
            vNoCities?.visibility = View.VISIBLE
        }
    }

    override fun checkCities() {
        getCitiesPresenter()?.refreshCities()
    }

    override fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(
            activity!!,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_LOCATION
        )
    }

    override fun setPermissionsResult(arePermissionsObtained: Boolean) {
        getCitiesPresenter()?.handlePermissionsResult(arePermissionsObtained)
    }

    private fun initView(view: View) {
        etSearch = view.findViewById(R.id.et_search)
        rvCities = view.findViewById(R.id.rv_cities)
        vNoCities = view.findViewById(R.id.v_no_cities)
        btnGetLocation = view.findViewById(R.id.btn_get_location)
        adapter = CitiesAdapter { name -> getCitiesPresenter()?.onCityClick(name) }
        rvCities?.adapter = adapter

        btnGetLocation?.setOnClickListener { _ -> getCitiesPresenter()?.tryGetLocation() }
        etSearch?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                getCitiesPresenter()?.searchCity(etSearch?.text.toString())
            }
            false
        }


        val touchHelper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP, ItemTouchHelper.LEFT) {
                override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                    getCitiesPresenter()?.tryRemoveCity(adapter?.getItemByPosition(viewHolder.adapterPosition))
                    adapter?.removeItem(viewHolder.adapterPosition)
                }
            })

        touchHelper.attachToRecyclerView(rvCities)
    }

    private fun getRouter(): IRouter? = if (activity is IRouter) activity as IRouter else null

    private fun getCitiesPresenter(): CitiesPresenter? {
        return if (getPresenter() != null) {
            getPresenter() as CitiesPresenter
        } else null
    }
}