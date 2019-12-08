package com.ar4i.weather.presentation.weather.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ar4i.weather.R
import com.ar4i.weather.data.models.DayWeatherVm
import com.ar4i.weather.data.models.HourlyVm
import com.ar4i.weather.presentation.base.view.BaseFragment
import com.ar4i.weather.presentation.weather.presenter.WeatherPresenter
import com.ar4i.weather.presentation.weather.view.adapter.DaysAdapter
import com.ar4i.weather.presentation.weather.view.adapter.HourlyAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class WeatherFragment : BaseFragment(), IWeatherView {

    companion object {
        const val EXTRA_CITY_NAME = "EXTRA_CITY_NAME"
        const val EXTRA_IS_FAVORITE = "EXTRA_IS_FAVORITE"

        fun newInstance(cityName: String, isFavorite: Boolean) = WeatherFragment().apply {
            arguments = Bundle().apply {
                putString(EXTRA_CITY_NAME, cityName)
                putBoolean(EXTRA_IS_FAVORITE, isFavorite)
            }
        }
    }

    private var tvCityName: TextView? = null
    private var tvDate: TextView? = null
    private var tvTime: TextView? = null
    private var ivCurrentCondition: ImageView? = null
    private var tvTemperatureLabel: TextView? = null
    private var tvDescription: TextView? = null
    private var tvAtmospherePressure: TextView? = null
    private var tvWindSpeed: TextView? = null
    private var tvHumidity: TextView? = null
    private var rvHourly: RecyclerView? = null
    private var rvDays: RecyclerView? = null
    private var fabAdd: FloatingActionButton? = null
    private var vCityNotFound: View? = null
    private var imgFavorite: View? = null

    private var daysAdapter: DaysAdapter? = null
    private var hourlyAdapter: HourlyAdapter? = null

    private var cityName: String? = null
    private var isFavorite: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            cityName = it.getString(EXTRA_CITY_NAME)
            isFavorite = it.getBoolean(EXTRA_IS_FAVORITE, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.fragment_weather

    override fun inject() {
        getComponent().inject(this)
    }

    override fun getCityName(): String? = cityName

    override fun isFavoriteCity(): Boolean? = isFavorite

    override fun setDays(days: List<DayWeatherVm>) {
        daysAdapter?.addAllAndNotify(days)
    }

    override fun setHourly(hourly: List<HourlyVm>) {
        hourlyAdapter?.addAllAndNotify(hourly)
    }

    override fun setCityName(cityName: String) {
        tvCityName?.text = cityName
    }

    override fun setDate(date: String) {
        tvDate?.text = date
    }

    override fun setTime(time: String) {
        tvTime?.text = time
    }

    override fun setCurrentCondition(url: String) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_error)
            .into(ivCurrentCondition)
    }

    override fun setDescription(description: String) {
        tvDescription?.text = description
    }

    override fun setTemperature(temp: String) {
        tvTemperatureLabel?.text = temp
    }

    override fun setPressure(pressure: String) {
        tvAtmospherePressure?.text = pressure
    }

    override fun setWindSpeed(speed: String) {
        tvWindSpeed?.text = speed
    }

    override fun setHumidity(humidity: String) {
        tvHumidity?.text = humidity
    }

    override fun showNotFoundError() {
        vCityNotFound?.visibility = View.VISIBLE
        showFab(false)
    }

    override fun disableFab() {
        fabAdd?.isEnabled = false
    }

    @SuppressLint("RestrictedApi")
    override fun showFab(show: Boolean) {
        if (show)
            fabAdd?.visibility = View.VISIBLE
        else
            fabAdd?.visibility = View.GONE
    }

    override fun showFavoriteImg() {
        imgFavorite?.visibility = View.VISIBLE
        imgFavorite?.alpha = 0.0f
        imgFavorite?.animate()
            ?.setDuration(600)
            ?.alpha(1.0f)
    }

    private fun initView(view: View) {
        tvCityName = view.findViewById(R.id.tv_city_name)
        tvDate = view.findViewById(R.id.tv_date)
        tvTime = view.findViewById(R.id.tv_time)
        ivCurrentCondition = view.findViewById(R.id.iv_current_condition)
        tvDescription = view.findViewById(R.id.tv_description)
        tvTemperatureLabel = view.findViewById(R.id.tv_temperature_label)
        tvAtmospherePressure = view.findViewById(R.id.tv_pressure_value)
        tvWindSpeed = view.findViewById(R.id.tv_wind_speed_value)
        tvHumidity = view.findViewById(R.id.tv_humidity_value)
        vCityNotFound = view.findViewById(R.id.v_city_not_found)
        imgFavorite = view.findViewById(R.id.img_favorite)

        rvHourly = view.findViewById(R.id.rv_hourly)
        rvDays = view.findViewById(R.id.rv_days)
        daysAdapter = DaysAdapter { day -> getWeatherPresenter()?.onDayClick(day) }
        hourlyAdapter = HourlyAdapter { hour -> getWeatherPresenter()?.onHourClick(hour, tvDate?.text.toString()) }
        rvDays?.adapter = daysAdapter
        rvHourly?.adapter = hourlyAdapter

        fabAdd = view.findViewById(R.id.fab_add)
        fabAdd?.setOnClickListener { getWeatherPresenter()?.trySaveCity() }

        rvDays?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                    showFab(false)
                else if (dy < 0)
                    showFab(true)
            }
        })
    }

    private fun getWeatherPresenter(): WeatherPresenter? {
        return if (getPresenter() != null) {
            getPresenter() as WeatherPresenter
        } else null
    }
}