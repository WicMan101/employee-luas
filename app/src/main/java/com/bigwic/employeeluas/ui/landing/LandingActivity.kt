package com.bigwic.employeeluas.ui.landing

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigwic.employeeluas.R
import com.bigwic.employeeluas.core.utils.AppUtils
import com.bigwic.employeeluas.databinding.ActivityLandingBinding
import com.bigwic.employeeluas.ui.landing.adapter.ForecastAdapter
import com.bigwic.employeeluas.ui.landing.viewmodel.LandingViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class LandingActivity : AppCompatActivity() {
    lateinit var binding: ActivityLandingBinding
    private val viewModel: LandingViewModel by viewModel()
    private val appUtils: AppUtils by inject()

    private var forecastAdapter = ForecastAdapter(emptyList(), null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.activity_landing, null, false)
        setContentView(binding.root)

        setSupportActionBar(binding.tBarLanding)
        setActionBarLoading(true, getString(R.string.loading))

        initializeViews()
        startObserving()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getForecast(if (appUtils.isEvening(Calendar.getInstance())) "sti" else "mar")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_refresh -> {
            setActionBarLoading(true, getString(R.string.loading))
            viewModel.getForecast(if (appUtils.isEvening(Calendar.getInstance())) "sti" else "mar")
            true
        } else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun initializeViews() {
        val forecastAdapter = ForecastAdapter(emptyList(), null)
        binding.rvStopInfo.apply {
            layoutManager = LinearLayoutManager(this@LandingActivity)
            adapter = forecastAdapter
        }
    }

    private fun startObserving() {
        viewModel.getForecastData().observe(this, { it ->
            it?.let {
                val direction = if (appUtils.isEvening(Calendar.getInstance())) "inbound" else "outbound"

                val directionInfo = it.direction?.first { e ->
                    e.name.lowercase() == direction
                }

                forecastAdapter = ForecastAdapter(directionInfo?.tram!!, directionInfo.name)
                binding.tvUpdated.text = appUtils.formatToReadableDate(it.created)
                binding.rvStopInfo.adapter = forecastAdapter
                setActionBarLoading(false, it.stop)
            }
        })
    }

    private fun setActionBarLoading(showLoading: Boolean, title: String) {
        when (showLoading) {
            true -> {
                binding.tBarLoading.visibility = View.VISIBLE
            }
            else -> {
                binding.tBarLoading.visibility = View.GONE
            }
        }

        binding.tBarLanding.title = title
    }
}