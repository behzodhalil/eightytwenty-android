package uz.behzod.eightytwenty.utils.extension

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


val Long.Companion.Zero
    get() = 0L

val Int.Companion.Zero
    get() = 0

val String.Companion.Empty
    get() = ""

fun String.asZoneDateTime(): ZonedDateTime {
   return ZonedDateTime.parse(this)
}