package com.bigwic.employeeluas.di

import android.content.Context
import com.bigwic.employeeluas.core.di.coreModule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules
import org.mockito.kotlin.mock

@RunWith(JUnit4::class)
class KoinModulesShould {
    private val mockContext = mock<Context>()

    @Test
    fun `verify koin modules`() {
        koinApplication {
            androidContext(mockContext)
            modules(appModule, networkModule, coreModule)
            checkModules()
        }
    }
}