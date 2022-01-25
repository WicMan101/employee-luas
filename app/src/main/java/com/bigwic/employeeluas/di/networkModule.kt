package com.bigwic.employeeluas.di

import com.bigwic.employeeluas.network.ForecastApi
import com.bigwic.employeeluas.network.repository.ForecastRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    factory { provideForecastApi(get()) }
    factory { ForecastRepository(get()) }
}

fun provideForecastApi(retrofit: Retrofit): ForecastApi {
    return retrofit.create(ForecastApi::class.java)
}