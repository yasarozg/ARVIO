package com.arflix.tv.data.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class EpisodeAbsoluteNumberTest {
    @Test
    fun `explicit absolute number is used when title has no number`() {
        val episode = episode(name = "Final", absolute = 139)
        assertEquals(139, episode.absoluteEpisodeNumberForVod())
    }

    @Test
    fun `numbered title wins over conflicting explicit absolute number`() {
        val episode = episode(name = "139. Bölüm", absolute = 136)
        assertEquals(139, episode.absoluteEpisodeNumberForVod())
    }

    @Test
    fun `turkish numbered episode title is used as fallback`() {
        assertEquals(139, episode(name = "139. Bölüm").absoluteEpisodeNumberForVod())
        assertEquals(139, episode(name = "Bölüm 139").absoluteEpisodeNumberForVod())
    }

    @Test
    fun `ordinary title does not invent an absolute number`() {
        assertNull(episode(name = "Yeni Başlangıç").absoluteEpisodeNumberForVod())
        assertNull(episode(name = "1. Bölüm").absoluteEpisodeNumberForVod())
    }

    private fun episode(name: String, absolute: Int? = null) = Episode(
        id = 1,
        episodeNumber = 1,
        seasonNumber = 5,
        name = name,
        absoluteEpisodeNumber = absolute
    )
}
