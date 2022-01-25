package com.bigwic.employeeluas.repository

import com.bigwic.employeeluas.model.Direction
import com.bigwic.employeeluas.model.StopInfo
import com.bigwic.employeeluas.model.Tram
import com.bigwic.employeeluas.network.ForecastApi
import com.bigwic.employeeluas.network.repository.ForecastRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class RepositoryShould {
    private val forecastApi = mock<ForecastApi>()
    private lateinit var forecastRepository: ForecastRepository

    private val created = "2022-01-25T10:29:29"
    private val stop = "Marlborough"
    private val stopAbv = "MAR"
    private val message = "Green Line services operating reduced frequency"

    private val name = "Outbound"
    private val trams = listOf(
        Tram("11", "Test"),
        Tram("DUE", "Test 2")
    )
    private val direction = listOf(Direction(name, trams))

    @Before
    fun setup() {
        runBlocking {
            whenever(forecastApi.getForecast("valid", "valid"))
                .thenReturn(
                    StopInfo(created, stop, stopAbv, message, direction)
                )
            whenever(forecastApi.getForecast("invalid", "invalid"))
                .thenReturn(
                    StopInfo("", "", "", "", emptyList())
                )
        }

        forecastRepository = ForecastRepository(forecastApi)
    }

    @Test
    fun `return valid data model response object`() {
        val stopInfo = StopInfo(created, stop, stopAbv, message, direction)

        runBlocking {
            assertEquals(stopInfo, forecastRepository.getForecast("valid", "valid"))
        }
    }

    @Test
    fun `return null object response`() {
        val stopInfo = StopInfo("", "", "", "", emptyList())

        runBlocking {
            assertEquals(stopInfo, forecastRepository.getForecast("invalid", "invalid"))
        }
    }
}