package com.bigwic.employeeluas.core.di

import android.content.Context
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.kotlin.mock

@RunWith(JUnit4::class)
class CoreModuleShould : KoinTest {
    private val mockContext = mock<Context>()

    @Test
    fun `verify koin modules`() {
        koinApplication {
            androidContext(mockContext)
            modules(coreModule)
            checkModules()
        }
    }
}