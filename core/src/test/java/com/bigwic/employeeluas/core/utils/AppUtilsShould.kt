package com.bigwic.employeeluas.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class AppUtilsShould {

    lateinit var appUtils: AppUtils
    val context = mock<Context>()
    val connectivityManager = mock<ConnectivityManager>()

    @Before
    fun setup() {
        appUtils = AppUtils()

        `when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(
            connectivityManager
        )
    }

    @Test
    fun `return false when capabilities are null`() {
        val isConnected = appUtils.isNetworkAvailable(context)
        assertFalse(isConnected, "no networks are available.")
    }

    @Test
    fun `return true when cellular capabilities are available`() {
        val capabilities = mock<NetworkCapabilities>{
            on { hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) } doReturn true
        }

        `when`(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)).thenReturn(
            capabilities
        )

        val isConnected = appUtils.isNetworkAvailable(context)
        assertTrue(isConnected, "Cellular network is available.")
    }

    @Test
    fun `return true when WiFi capabilities are available`() {
        val capabilities = mock<NetworkCapabilities>{
            on { hasTransport(NetworkCapabilities.TRANSPORT_WIFI) } doReturn true
        }

        `when`(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)).thenReturn(
            capabilities
        )

        val isConnected = appUtils.isNetworkAvailable(context)
        assertTrue(isConnected, "WiFi network is available.")
    }

    @Test
    fun `return true when ethernet capabilities are available`() {
        val capabilities = mock<NetworkCapabilities>{
            on { hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) } doReturn true
        }

        `when`(connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)).thenReturn(
            capabilities
        )

        val isConnected = appUtils.isNetworkAvailable(context)
        assertTrue(isConnected, "ethernet network is available.")
    }
}