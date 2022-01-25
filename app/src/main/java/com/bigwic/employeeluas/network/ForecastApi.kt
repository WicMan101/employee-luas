package com.bigwic.employeeluas.network

import com.bigwic.employeeluas.model.StopInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface ForecastApi {
    @GET("action={action}}&stop={stop}&encrypt=false")
    suspend fun getForecast(@Path("action") action: String,
                            @Path("stop") stop: String)
    : StopInfo
}