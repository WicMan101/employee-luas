package com.bigwic.employeeluas.di

import com.bigwic.employeeluas.ui.landing.viewmodel.LandingViewModel
import org.koin.dsl.module

val appModule = module {
    factory { LandingViewModel(get()) }
}