package com.ar4i.weather.data.repositories.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import java.util.*

@SuppressLint("StaticFieldLeak")
object LocationRepository : ILocationRepository {

    private lateinit var context: Context
    private lateinit var locationManager: LocationManager
    private lateinit var geocoder: Geocoder

    fun setContext(context: Context) {
        this.context = context
        this.locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        this.geocoder = Geocoder(this.context, Locale.getDefault())
    }

    override fun hasPermissions(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        } else
            true
    }

    override fun getCityNameByLocation(): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            ) {
                val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                tryGetCityName(location)
            } else {
                null
            }
        } else {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            tryGetCityName(location)
        }
    }

    private fun tryGetCityName(location: Location?): String? {
        return if (location != null) {
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            return addresses?.first()?.locality
        } else null
    }
}