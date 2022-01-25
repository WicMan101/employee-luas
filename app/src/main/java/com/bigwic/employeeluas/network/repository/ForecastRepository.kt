package com.bigwic.employeeluas.network.repository

import com.bigwic.employeeluas.network.ForecastApi

class ForecastRepository(private val forecastApi: ForecastApi) {
    suspend fun getForecast(action: String, stop: String) =
        forecastApi.getForecast(action, stop)
}