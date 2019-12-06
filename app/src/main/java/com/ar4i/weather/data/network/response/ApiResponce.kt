package com.ar4i.weather.data.network.response

import com.google.gson.annotations.SerializedName


data class ApiResponce(
    @SerializedName("data") val data: Data?
)

data class Data(
    @SerializedName("current_condition") val currentCondition: List<CurrentCondition>?,
    @SerializedName("request") val request: List<RequestInfo>?,
    @SerializedName("weather") val weather: List<Weather>?,
    @SerializedName("error") val error: Error?
)

data class RequestInfo(
    @SerializedName("type") val type: String,
    @SerializedName("query") val query: String
)

data class CurrentCondition(
    @SerializedName("observation_time") val observationTime: String,
    @SerializedName("temp_C") val temp: Int,
    @SerializedName("weatherCode") val code: Int,
    @SerializedName("weatherDesc") val descriptions: List<Description>?,
    @SerializedName("windspeedKmph") val windSpeed: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("pressure") val pressure: Int
)

data class Weather(
    @SerializedName("date") val date: String,
    @SerializedName("maxtempC") val maxTemp: Int,
    @SerializedName("mintempC") val minTemp: Int,
    @SerializedName("avgtempC") val avgTemp: Int,
    @SerializedName("hourly") val hourly: List<Hourly>?
)


data class Hourly(
    @SerializedName("time") val time: String,
    @SerializedName("tempC") val temp: Int,
    @SerializedName("windspeedKmph") val windSpeed: Int,
    @SerializedName("weatherCode") val code: Int,
    @SerializedName("weatherDesc") val descriptions: List<Description>?,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("pressure") val pressure: Int
)

data class Description(
    @SerializedName("value") val value: String
)

data class Error(
    @SerializedName("msg") val message: String
)