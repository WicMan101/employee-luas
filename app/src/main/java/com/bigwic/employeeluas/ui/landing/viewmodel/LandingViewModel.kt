package com.bigwic.employeeluas.ui.landing.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigwic.employeeluas.model.StopInfo
import com.bigwic.employeeluas.network.repository.ForecastRepository
import kotlinx.coroutines.launch
import java.util.*

class LandingViewModel(private val forecastRepository: ForecastRepository) : ViewModel() {
    private val forecastData = MutableLiveData<StopInfo>()

    fun getForecastData(): MutableLiveData<StopInfo> {
        return forecastData;
    }

    fun getForecast(stop: String) {
        viewModelScope.launch {
            try {
                val result = forecastRepository.getForecast("forecast", stop)
                forecastData.value = result
            } catch (ex: Exception) {
                Log.e("TAG", "getForecast: ${ex.message}", ex)
            }
        }
    }
}