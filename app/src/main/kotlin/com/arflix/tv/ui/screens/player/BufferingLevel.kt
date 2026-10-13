package com.arflix.tv.ui.screens.player

import androidx.datastore.preferences.core.stringPreferencesKey

enum class BufferingLevel(
    val minBufferMs: Int,
    val maxBufferMs: Int,
    val bufferForPlaybackMs: Int,
    val bufferForPlaybackAfterRebufferMs: Int
) {
    Low(5_000, 15_000, 1_000, 2_000),
    Medium(15_000, 30_000, 2_000, 4_000),
    High(25_000, 50_000, 3_000, 6_000),
    Highest(35_000, 70_000, 5_000, 10_000);

    fun next(): BufferingLevel = entries[(ordinal + 1) % entries.size]

    companion object {
        fun fromPreference(value: String?): BufferingLevel =
            entries.firstOrNull { it.name.equals(value, ignoreCase = true) } ?: Medium
    }
}

val BUFFERING_LEVEL_KEY = stringPreferencesKey("buffering_level")
