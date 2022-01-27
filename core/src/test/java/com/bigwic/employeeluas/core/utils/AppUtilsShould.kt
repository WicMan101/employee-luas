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
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


@RunWith(JUnit4::class)
class AppUtilsShould {

    lateinit var appUtils: AppUtils
    private val mockContext = mock<Context>()
    private val mockConnectivityManager = mock<ConnectivityManager>()

    @Before
    fun setup() {
        appUtils = AppUtils(mockContext)

        `when`(mockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(
            mockConnectivityManager
        )
    }

    @Test
    fun `return false when capabilities are null`() {
        val isConnected = appUtils.isNetworkAvailable()
        assertFalse(isConnected, "no networks are available.")
    }

    @Test
    fun `return true when cellular capabilities are available`() {
        val capabilities = mock<NetworkCapabilities>{
            on { hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) } doReturn true
        }

        `when`(mockConnectivityManager.getNetworkCapabilities(mockConnectivityManager.activeNetwork)).thenReturn(
            capabilities
        )

        val isConnected = appUtils.isNetworkAvailable()
        assertTrue(isConnected, "Cellular network is available.")
    }

    @Test
    fun `return true when WiFi capabilities are available`() {
        val capabilities = mock<NetworkCapabilities>{
            on { hasTransport(NetworkCapabilities.TRANSPORT_WIFI) } doReturn true
        }

        `when`(mockConnectivityManager.getNetworkCapabilities(mockConnectivityManager.activeNetwork)).thenReturn(
            capabilities
        )

        val isConnected = appUtils.isNetworkAvailable()
        assertTrue(isConnected, "WiFi network is available.")
    }

    @Test
    fun `return true when ethernet capabilities are available`() {
        val capabilities = mock<NetworkCapabilities>{
            on { hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) } doReturn true
        }

        `when`(mockConnectivityManager.getNetworkCapabilities(mockConnectivityManager.activeNetwork)).thenReturn(
            capabilities
        )

        val isConnected = appUtils.isNetworkAvailable()
        assertTrue(isConnected, "ethernet network is available.")
    }

    @Test
    fun `format date to readable date`() {
        val unreadableDate = "2022-01-24T23:35:50"
        val readableDate = appUtils.formatToReadableDate(unreadableDate)
        assertEquals("24/01/2022 23:35", readableDate)
    }

    @Test
    fun `return isEvening true when providing 23h59m`() {
        val currentTime = Calendar.getInstance()
        currentTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(23))
        currentTime.set(Calendar.MINUTE, Integer.valueOf(59))
        assertTrue(appUtils.isEvening(currentTime))
    }

    @Test
    fun `return isEvening true when providing 12h01m`() {
        val currentTime = Calendar.getInstance()
        currentTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(12))
        currentTime.set(Calendar.MINUTE, Integer.valueOf(1))
        assertTrue(appUtils.isEvening(currentTime))
    }

    @Test
    fun `return isEvening true when providing 18h0m`() {
        val currentTime = Calendar.getInstance()
        currentTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(18))
        currentTime.set(Calendar.MINUTE, Integer.valueOf(0))
        assertTrue(appUtils.isEvening(currentTime))
    }

    @Test
    fun `return isEvening false when providing 00h00m`() {
        val currentTime = Calendar.getInstance()
        currentTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(12))
        currentTime.set(Calendar.MINUTE, Integer.valueOf(0))
        assertFalse(appUtils.isEvening(currentTime))
    }

    @Test
    fun `return isEvening false when providing 12m00s`() {
        val currentTime = Calendar.getInstance()
        currentTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(12))
        currentTime.set(Calendar.MINUTE, Integer.valueOf(0))
        assertFalse(appUtils.isEvening(currentTime))
    }

    @Test
    fun `return isEvening false when providing 10h0m`() {
        val currentTime = Calendar.getInstance()
        currentTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(10))
        currentTime.set(Calendar.MINUTE, Integer.valueOf(0))
        assertFalse(appUtils.isEvening(currentTime))
    }
}