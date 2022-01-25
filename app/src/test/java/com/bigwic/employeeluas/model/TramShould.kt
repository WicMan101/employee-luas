package com.bigwic.employeeluas.model

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class TramShould {
    private val dueMins = "11"
    private val destination = "Sandyford"

    @Test
    fun `return valid model with provided params`() {
        val tram = Tram(dueMins, destination)
        assertNotNull(tram)
        assertEquals(dueMins, tram.dueMins)
        assertEquals(destination, tram.destination)
    }
}