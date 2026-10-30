package com.arflix.tv.util

import org.junit.Assert.assertEquals
import org.junit.Test

class AppDateFormattingTest {
    @Test
    fun dateUsesTheSelectedAppLanguage() {
        assertEquals("30 Oca 2026", formatAppDate("2026-01-30", "tr-TR"))
    }

    @Test
    fun invalidDateIsReturnedUnchanged() {
        assertEquals("unknown", formatAppDate("unknown", "tr-TR"))
    }
}
