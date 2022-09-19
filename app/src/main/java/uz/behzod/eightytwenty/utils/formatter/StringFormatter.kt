package uz.behzod.eightytwenty.utils.formatter

import android.content.Context
import uz.behzod.eightytwenty.utils.constants.*
import java.time.DayOfWeek

object StringFormatter {

    fun asDayOfWeek(context: Context, isShort: Boolean, lists: List<Int>): String {
        val builder = StringBuilder()

        lists.forEachIndexed { _, item ->
            val resourceId = StringUtils.getStringFromResourceForDay(item, isShort)
            builder.append(context.getString(resourceId))
        }
        return builder.toString()
    }

    fun parseDayOfWeek(day: Int): List<Int> {
        val days: MutableList<Int> = mutableListOf()

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