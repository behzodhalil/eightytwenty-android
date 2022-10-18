package uz.behzod.eightytwenty.utils.formatter

import uz.behzod.eightytwenty.R
import java.time.DayOfWeek

object StringUtils {

    fun getStringFromResourceForDay(day: Int, isShort: Boolean): Int {
        val days: Int

        if (isShort) {
            days = when (day) {
                DayOfWeek.MONDAY.value -> {
                    R.string.text_abbreviation_mon
                }
                DayOfWeek.TUESDAY.value -> {
                    R.string.text_abbreviation_tue
                }
                DayOfWeek.WEDNESDAY.value -> {
                    R.string.text_abbreviation_wed
                }
                DayOfWeek.THURSDAY.value -> {
                    R.string.text_abbreviation_thurs
                }
                DayOfWeek.FRIDAY.value -> {
                    R.string.text_abbreviation_fri
                }
                DayOfWeek.SATURDAY.value -> {
                    R.string.text_abbreviation_sat
                }
                DayOfWeek.SUNDAY.value -> {
                    R.string.text_abbreviation_sun
                }
                else -> 0
            }
        } else {
            days = when (day) {
                DayOfWeek.MONDAY.value -> {
                    R.string.text_monday
                }
                DayOfWeek.TUESDAY.value -> {
                    R.string.text_tuesday
                }
                DayOfWeek.WEDNESDAY.value -> {
                    R.string.text_wednesday
                }
                DayOfWeek.THURSDAY.value -> {
                    R.string.text_thursday
                }
                DayOfWeek.FRIDAY.value -> {
                    R.string.text_friday
                }
                DayOfWeek.SATURDAY.value -> {
                    R.string.text_saturday
                }
                DayOfWeek.SUNDAY.value -> {
                    R.string.text_sunday
                }
                else -> 0
            }
        }

        return days
    }
}