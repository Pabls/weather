package com.ar4i.weather.data.mappers

interface IErrorHandler {
    fun getErrorMessage(t: Throwable): String
}