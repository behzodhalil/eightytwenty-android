package uz.behzoddev.ui_collapsible_calendar

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

const val YEAR = 1
const val MONTH = 2
const val DAY_OF_MONTH = 5

@Parcelize
data class Day(
    val year: Int,
    val month: Int,
    val day: Int = 0,
) : Parcelable

fun Day.different(): Int {
    val todayOfCalendar = Calendar.getInstance()
    val day = Day(
        year = todayOfCalendar[YEAR],
        month = todayOfCalendar[MONTH],
        day = todayOfCalendar[DAY_OF_MONTH])
    return ((toUnixTime() - day.toUnixTime())
            / (1000 * 60 * 60 * 24)).toInt()
}

fun Day.toUnixTime(): Long {
    val date = Date(year, month, day)
    return date.time
}

