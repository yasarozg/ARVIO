package com.arflix.tv.ui.screens.player

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BufferingLevelTest {
    @Test
    fun `unknown and legacy default values migrate to medium`() {
        assertEquals(BufferingLevel.Medium, BufferingLevel.fromPreference("legacy-value"))
        assertEquals(BufferingLevel.Medium, BufferingLevel.fromPreference("Default"))
        assertEquals(BufferingLevel.Medium, BufferingLevel.fromPreference(null))
    }

    @Test
    fun `levels have fixed buffer durations and at least one second startup`() {
        assertEquals(15_000, BufferingLevel.Low.maxBufferMs)
        assertEquals(30_000, BufferingLevel.Medium.maxBufferMs)
        assertEquals(50_000, BufferingLevel.High.maxBufferMs)
        assertEquals(70_000, BufferingLevel.Highest.maxBufferMs)
        BufferingLevel.entries.forEach { level ->
            assertTrue(level.bufferForPlaybackMs >= 1_000)
        }
    }

    @Test
    fun `cycle visits all levels and returns to medium`() {
        var level = BufferingLevel.Medium
        repeat(BufferingLevel.entries.size) { level = level.next() }
        assertEquals(BufferingLevel.Medium, level)
    }
}
