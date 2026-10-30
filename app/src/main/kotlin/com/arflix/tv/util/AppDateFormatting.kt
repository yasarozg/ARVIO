package com.arflix.tv.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.Date
import java.text.DateFormat

/** Central display formatting for dates, using the app language instead of the device locale. */
fun formatAppDate(rawDate: String?, languageTag: String, fallback: String = ""): String {
    val value = rawDate?.trim().orEmpty()
    if (value.isEmpty()) return fallback
    val locale = appLocale(languageTag)
    val date = try {
        LocalDate.parse(value)
    } catch (_: Exception) {
        null
    }
        ?: parseLegacyDisplayDate(value)
        ?: return value
    return date.format(DateTimeFormatter.ofPattern("d MMM uuuu", locale))
}

private fun parseLegacyDisplayDate(value: String): LocalDate? {
    val parser = SimpleDateFormat("d MMM yyyy", java.util.Locale.ENGLISH).apply { isLenient = false }
    val position = ParsePosition(0)
    val parsed = parser.parse(value, position)
    if (parsed == null || position.index != value.length) return null
    return parsed.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
}

fun formatAppDateTime(timestampMs: Long, languageTag: String): String =
    DateFormat.getDateTimeInstance(
        DateFormat.SHORT,
        DateFormat.SHORT,
        appLocale(languageTag)
    ).format(Date(timestampMs))
