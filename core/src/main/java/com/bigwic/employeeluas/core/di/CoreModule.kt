package com.bigwic.employeeluas.core.di

import android.content.Context
import android.util.Log
import com.bigwic.employeeluas.core.BuildConfig
import com.bigwic.employeeluas.core.utils.AppUtils
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.converter.htmlescape.HtmlEscapeStringConverter
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

val coreModule = module {
    single { provideAppUtils(androidContext()) }
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
}

fun provideAppUtils(context: Context): AppUtils {
    return AppUtils(context)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(
            TikXmlConverterFactory.create(
                TikXml.Builder()
                    .exceptionOnUnreadXml(false)
                    .addTypeConverter(String.javaClass, HtmlEscapeStringConverter()).build()))
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient()
        .newBuilder()
        .addInterceptor(interceptor)
        .build()
}