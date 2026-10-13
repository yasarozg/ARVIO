package com.arflix.tv.ui.screens.player

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BufferingLevelTest {
    @Test
    fun `unknown persisted value remains backward compatible`() {
        assertEquals(BufferingLevel.Default, BufferingLevel.fromPreference("legacy-value"))
        assertEquals(BufferingLevel.Default, BufferingLevel.fromPreference(null))
    }

    @Test
    fun `highest level caps every duration at ten seconds`() {
        val level = BufferingLevel.Highest
        assertEquals(10_000, level.minBufferMs)
        assertEquals(10_000, level.maxBufferMs)
        assertEquals(5_000, level.bufferForPlaybackMs)
        assertEquals(10_000, level.bufferForPlaybackAfterRebufferMs)
        assertNull(BufferingLevel.Default.maxBufferMs)
    }

    @Test
    fun `cycle visits all levels and returns to default`() {
        var level = BufferingLevel.Default
        repeat(BufferingLevel.entries.size) { level = level.next() }
        assertEquals(BufferingLevel.Default, level)
    }
}
