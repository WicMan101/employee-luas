package com.bigwic.employeeluas.core.di

import android.content.Context
import com.bigwic.employeeluas.core.BuildConfig
import com.bigwic.employeeluas.core.utils.AppUtils
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val coreModule = module {
    single { provideAppUtils(androidContext()) }
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
}

fun provideAppUtils(context: Context): AppUtils {
    return AppUtils(context)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}