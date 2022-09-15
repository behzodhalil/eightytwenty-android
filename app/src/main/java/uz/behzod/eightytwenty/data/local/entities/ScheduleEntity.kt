package uz.behzod.eightytwenty.data.local.entities

import androidx.annotation.StringRes
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.LocalTime

data class ScheduleEntity(
    val frequencyTypes: Int = Int.Zero,
    val daysOfWeek: Int = Int.Zero,
    val dateOfCompletion: LocalTime? = null,
    val uid: Long = Long.Zero
) {

    @StringRes
    fun getStringFromResourceForDay(day: Int): Int {
        val dayOfWeek = when (day) {
            DaysOfWeek.MONDAY.ordinal -> {
                R.string.text_abbreviation_mon
            }
            DaysOfWeek.TUESDAY.ordinal -> {
                R.string.text_abbreviation_tue
            }
            DaysOfWeek.WEDNESDAY.ordinal -> {
                R.string.text_abbreviation_wed
            }
            DaysOfWeek.THURSDAY.ordinal -> {
                R.string.text_abbreviation_thurs
            }
            DaysOfWeek.FRIDAY.ordinal -> {
                R.string.text_abbreviation_fri
            }
            DaysOfWeek.SATURDAY.ordinal -> {
                R.string.text_abbreviation_sat
            }
            DaysOfWeek.SUNDAY.ordinal -> {
                R.string.text_abbreviation_sun
            }
            else -> 0
        }
        return dayOfWeek
    }

    @StringRes
    fun getStringFromResourceForFrequencyType(type: Int): Int {
        val typeOfFrequency = when (type) {
            FrequencyTypes.DAILY.ordinal -> {
                R.string.text_frequency_daily
            }
            FrequencyTypes.WEEKLY.ordinal -> {
                R.string.text_frequency_weekly
            }
            FrequencyTypes.RANDOM.ordinal -> {
                R.string.text_frequency_random
            }
            else -> 0
        }
        return typeOfFrequency
    }
}

enum class DaysOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

private enum class FrequencyTypes {
    DAILY,
    WEEKLY,
    RANDOM
}
