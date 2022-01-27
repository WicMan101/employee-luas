package com.bigwic.employeeluas.model

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.verify
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(JUnit4::class)
class StopInfoShould {
    private val created = "2022-01-25T10:29:29"
    private val stop = "Marlborough"
    private val stopAbv = "MAR"
    private val message = "Green Line services operating reduced frequency"

    private val name = "Outbound"
    private val trams = listOf(
        Tram("11", "Test"),
        Tram("DUE", "Test 2")
    )
    private var direction = listOf(Direction(name, trams))

    @Test
    fun `return valid model with provided params`() {
        val stopInfo = StopInfo(created, stop, stopAbv, message, direction)
        assertNotNull(stopInfo)
        assertEquals(created, stopInfo.created)
        assertEquals(stop, stopInfo.stop)
        assertEquals(stopAbv, stopInfo.stopAbv)
        assertEquals(message, stopInfo.message)
        assertEquals(direction, stopInfo.direction)
        assertEquals(direction[0].name, stopInfo.direction!![0].name)
        assertEquals(direction[0].tram, stopInfo.direction!![0].tram)
    }

    @Test
    fun `handle empty directions list without exception`() {
        direction = emptyList()
        val stopInfo = StopInfo(created, stop, stopAbv, message, direction)
        assertEquals(emptyList(), stopInfo.direction)
    }
}