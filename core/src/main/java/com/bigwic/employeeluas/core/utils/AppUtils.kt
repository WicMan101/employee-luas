package com.bigwic.employeeluas.core.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

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

    @SuppressLint("SimpleDateFormat")
    fun formatToReadableDate(createdDate: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
        return formatter.format(parser.parse(createdDate))
    }

    // Bit of a long way round method.
    // Passing in Calendar in order to make the method testable.
    fun isEvening(currentTime: Calendar): Boolean {
        val fromTime = Calendar.getInstance()
        fromTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(12))
        fromTime.set(Calendar.MINUTE, Integer.valueOf(0))

        val toTime = Calendar.getInstance()
        toTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(23))
        toTime.set(Calendar.MINUTE, Integer.valueOf(59))
        toTime.set(Calendar.SECOND, Integer.valueOf(59))

        return currentTime.after(fromTime) && currentTime.before(toTime)
    }
}