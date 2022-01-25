package com.bigwic.employeeluas

import android.app.Application
import com.bigwic.employeeluas.core.di.coreModule
import com.bigwic.employeeluas.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Koin initialization
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(coreModule, networkModule)
        }
    }
}