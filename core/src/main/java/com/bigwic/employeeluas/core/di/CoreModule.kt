package com.bigwic.employeeluas.core.di

import android.content.Context
import com.bigwic.employeeluas.core.utils.AppUtils
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { provideAppUtils(androidContext()) }
}

fun provideAppUtils(context: Context): AppUtils {
    return AppUtils(context)
}