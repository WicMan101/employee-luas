package com.bigwic.employeeluas.model

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class DirectionShould {
    private val name = "Outbound"
    private val trams = listOf(
        Tram("11", "Test"),
        Tram("DUE", "Test 2")
    )

    @Test
    fun `return valid model with provided params`() {
        val direction = Direction(name, trams)
        assertNotNull(direction)
        assertEquals(name, direction.name)
        assertEquals(trams, direction.trams)
    }
}