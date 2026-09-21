package com.arflix.tv.ui.screens.player

import androidx.datastore.preferences.core.stringPreferencesKey

enum class BufferingLevel(val minBufferMs: Int?, val maxBufferMs: Int?, val bufferForPlaybackMs: Int?, val bufferForPlaybackAfterRebufferMs: Int?) {
    Default(null, null, null, null),
    Low(2_500, 5_000, 500, 1_000),
    Medium(5_000, 7_500, 1_000, 2_000),
    High(7_500, 10_000, 2_500, 5_000),
    Highest(10_000, 10_000, 5_000, 10_000);
    fun next(): BufferingLevel = entries[(ordinal + 1) % entries.size]
    companion object {
        fun fromPreference(value: String?): BufferingLevel = entries.firstOrNull { it.name.equals(value, ignoreCase = true) } ?: Default
    }
}

val BUFFERING_LEVEL_KEY = stringPreferencesKey("buffering_level")
