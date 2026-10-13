package com.arflix.tv.data.repository

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class IptvEpisodeFallbackTest {
    @Test
    fun `flattened fallback is never used across seasons`() {
        assertTrue(canUseFlattenedEpisodeFallback(requestedSeason = 1))
        assertFalse(canUseFlattenedEpisodeFallback(requestedSeason = 5))
    }
}
