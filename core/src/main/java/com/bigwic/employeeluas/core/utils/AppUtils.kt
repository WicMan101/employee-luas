package com.bigwic.employeeluas.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class AppUtils(private val context: Context) {
    fun isNetworkAvailable(): Boolean {
        var hasConnection = false

        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities = connectivityManager
            .getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    hasConnection = true
                }
            }
        }
        return hasConnection

    }
}