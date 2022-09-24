package uz.behzod.eightytwenty.data.local.entities

import android.content.Context
import androidx.annotation.StringRes
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import uz.behzod.eightytwenty.R
import uz.behzod.eightytwenty.utils.constants.*
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import uz.behzod.eightytwenty.utils.extension.debugger
import java.time.DayOfWeek

@Entity(
    tableName = "schedule_table",
    foreignKeys = [
        ForeignKey(
            entity = HabitEntity::class,
            parentColumns = ["uid"],
            childColumns = ["schedule_habit_uid"],
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["task_uid"],
            childColumns = arrayOf("schedule_task_uid"),
            onDelete = CASCADE
        )
    ],
    indices = [Index(value = ["schedule_habit_uid","schedule_task_uid"], unique = true)]
)
data class ScheduleEntity(
    val frequencyTypes: Int = Int.Zero,
    val daysOfWeek: Int = Int.Zero,
    val dateOfCompletion: String = String.Empty,
    @ColumnInfo(name = "schedule_habit_uid") val habitId: Long? = null,
    @ColumnInfo(name = "schedule_task_uid") val taskUid: Long? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "schedule_uid")
    val uid: Long = Long.Zero
) {

    fun parseDaysOfWeek(): List<Int> {
        return Companion.parseDaysOfWeek(daysOfWeek)
    }

    fun parseFrequencyTypes(): List<Int> {
        return Companion.parseFrequencyType(frequencyTypes)
    }

    // Ushbu funksiyamiz, o'qishga oson bo'lgan shaklga o'zgartirishga yordam beradi.
    // Context orqali, biz getString() funksiyasini chaqiramiz.
    // Eng oxirgi qismda, stringBuilder.append() orqali biz string ni qaytaramiz.

    fun formatDaysOfWeek(context: Context, isAbbreviated: Boolean): String {
        val stringBuilder = StringBuilder()
        val list = parseDaysOfWeek()

        list.forEachIndexed { _, item ->
            val resourceId = if (isAbbreviated) {
                getStringFromResourceForDayAbbreviated(item)
            } else {
                setStringForDayOfWeek(item)
            }

            stringBuilder.append(context.getString(resourceId))
        }

        return stringBuilder.toString()
    }


    fun formatAsFrequencyTypes(context: Context): String {
        val builder = StringBuilder()
        val list = parseFrequencyTypes()

        list.forEachIndexed { index, i ->
            val resourceId = getStringFromResourceForFrequencyType(i)

            builder.append(context.getString(resourceId))
        }

        return builder.toString()
    }

    // Ushbu metodimiz hafta kunlarini tanlab olib, bizga
    // hafta kunlarining ismini qaytarishga yordam beradi.
    // Shuningdek, ushbu metodimiz hafta kunlarining qisqa shakldagi
    // ismini qaytaradi

    @StringRes
    fun getStringFromResourceForDayAbbreviated(day: Int): Int {
        val dayOfWeek = when (day) {
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
        return dayOfWeek
    }

    // Ushbu metodimiz hafta kunlarini tanlab olib, bizga
    // hafta kunlarining ismini qaytarishga yordam beradi.
    // Shuningdek, ushbu metodimiz hafta kunlarining to'liq
    // ismini qaytaradi.
    @StringRes
    fun setStringForDayOfWeek(day: Int): Int {
        val dayOfWeek = when (day) {
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

    companion object {


        fun parseFrequencyType(type: Int): List<Int> {
            val types = mutableListOf<Int>()

            if (type and 1 == BIT_VALUE_DAILY) types.add(FrequencyTypes.DAILY.ordinal)
            if (type and 2 == BIT_VALUE_WEEKLY) types.add(FrequencyTypes.WEEKLY.ordinal)
            if (type and 4 == BIT_VALUE_RANDOM) types.add(FrequencyTypes.RANDOM.ordinal)

            return types
        }

        fun parseDaysOfWeek(day: Int): List<Int> {
            val days = mutableListOf<Int>()

            if (day and 1 == BIT_VALUE_OF_MONDAY) days.add(DayOfWeek.MONDAY.value)
            if (day and 2 == BIT_VALUE_OF_TUESDAY) days.add(DayOfWeek.TUESDAY.value)
            if (day and 4 == BIT_VALUE_OF_WEDNESDAY) days.add(DayOfWeek.WEDNESDAY.value)
            if (day and 8 == BIT_VALUE_OF_THURSDAY) days.add(DayOfWeek.THURSDAY.value)
            if (day and 16 == BIT_VALUE_OF_FRIDAY) days.add(DayOfWeek.FRIDAY.value)
            if (day and 32 == BIT_VALUE_OF_SATURDAY) days.add(DayOfWeek.SATURDAY.value)
            if (day and 64 == BIT_VALUE_OF_SUNDAY) days.add(DayOfWeek.SUNDAY.value)

            return days
        }
    }
}

private enum class FrequencyTypes {
    DAILY,
    WEEKLY,
    RANDOM
}
