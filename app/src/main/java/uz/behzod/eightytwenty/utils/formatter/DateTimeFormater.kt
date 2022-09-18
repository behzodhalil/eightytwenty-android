package uz.behzod.eightytwenty.utils.formatter

import android.content.Context
import android.text.format.DateFormat
import java.time.format.DateTimeFormatter

object DateTimeFormatter {

    private const val PATTERN_TIME_12_HOUR = "H:MM A"
    private const val PATTERN_TIME_24_HOUR = "H:MM"
    private const val PATTERN_TIME_12_HOUR_SHORT = "EEE,d,MMM H:mm A"
    private const val PATTERN_TIME_24_HOUR_SHORT = "EEE,d,MMM H:mm "
    private const val PATTERN_DATE_WEEK_AND_MONTH = "EEE,d,MMM"

    fun asTime(context: Context, isShort: Boolean): DateTimeFormatter {
        val isTwentyFourHour = DateFormat.is24HourFormat(context)

        val pattern = if (isShort) {
            if (isTwentyFourHour) {
                PATTERN_TIME_24_HOUR_SHORT
            } else {
                PATTERN_TIME_12_HOUR_SHORT
            }
        } else {
            if (isTwentyFourHour) {
                PATTERN_TIME_24_HOUR_SHORT
            } else {
                PATTERN_TIME_12_HOUR_SHORT
            }
        }
        return DateTimeFormatter.ofPattern(pattern)
    }

    fun asTime(context: Context): DateTimeFormatter {
        val isTwentyFourHour = DateFormat.is24HourFormat(context)

        val pattern = if (isTwentyFourHour) {
            PATTERN_TIME_24_HOUR
        } else {
            PATTERN_TIME_12_HOUR
        }

        return DateTimeFormatter.ofPattern(pattern)
    }

    fun asMonthAndWeek(context: Context): DateTimeFormatter {
        return DateTimeFormatter.ofPattern(PATTERN_DATE_WEEK_AND_MONTH)
    }
}