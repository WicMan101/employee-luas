package com.bigwic.employeeluas.ui.landing.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bigwic.employeeluas.model.Direction
import com.bigwic.employeeluas.model.StopInfo
import com.bigwic.employeeluas.model.Tram
import com.bigwic.employeeluas.network.ForecastApi
import com.bigwic.employeeluas.network.repository.ForecastRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.test.KoinTest
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.timeout
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(JUnit4::class)
class LandingViewModelShould : KoinTest {
    private lateinit var viewModel: LandingViewModel
    private val mockForecastApi = mock<ForecastApi>()
    private val mockObserver = mock<Observer<StopInfo>>()

    private lateinit var forecastRepository: ForecastRepository

    @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val mal_created = "2022-01-25T10:29:29"
    private val mal_stop = "Marlborough"
    private val mal_stopAbv = "MAR"
    private val mal_message = "Green Line services operating reduced frequency"

    private val mal_name = "Outbound"
    private val mal_trams = listOf(
        Tram("11", "Mal Test"),
        Tram("DUE", "Mal Test 2")
    )
    private val mal_direction = listOf(Direction(mal_name, mal_trams))


    private val sti_created = "2022-01-20T11:03:02"
    private val sti_stop = "Stillorgan"
    private val sti_stopAbv = "STI"
    private val sti_message = "Green Line services operating reduced frequency"

    private val sti_name = "Inbound"
    private val sti_trams = listOf(
        Tram("2", "Sti Test"),
        Tram("8", "Sti Test 2")
    )
    private val sti_direction = listOf(Direction(sti_name, sti_trams))

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        forecastRepository = ForecastRepository(mockForecastApi)
        viewModel = LandingViewModel(forecastRepository)

        runBlocking {
            whenever(forecastRepository.getForecast("forecast", "mal"))
                .thenReturn(
                    StopInfo(mal_created, mal_stop, mal_stopAbv, mal_message, mal_direction)
                )
            whenever(forecastRepository.getForecast("forecast", "sti"))
                .thenReturn(
                    StopInfo(sti_created, sti_stop, sti_stopAbv, sti_message, sti_direction)
                )
        }

        viewModel.getForecastData().observeForever(mockObserver)
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `return valid StopInfo LiveData for mal`() {
        viewModel.getForecast("mal")
        verify(mockObserver, timeout(50)).onChanged(StopInfo(mal_created, mal_stop, mal_stopAbv, mal_message, mal_direction))
    }

    @Test
    fun `return valid StopInfo LiveData for sti`() {
        viewModel.getForecast("sti")
        verify(mockObserver, timeout(50)).onChanged(StopInfo(sti_created, sti_stop, sti_stopAbv, sti_message, sti_direction))
    }
}