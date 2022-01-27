package com.bigwic.employeeluas.network

import com.bigwic.employeeluas.model.StopInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForecastApi {
    @GET("get.ashx")
    suspend fun getForecast(@Query("action") action: String,
                            @Query("stop") stop: String,
                            @Query("encrypt") query: String = "false")
    : StopInfo
}