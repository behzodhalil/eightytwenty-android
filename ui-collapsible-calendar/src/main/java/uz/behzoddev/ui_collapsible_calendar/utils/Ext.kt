package uz.behzoddev.ui_collapsible_calendar.utils

import java.util.*

/**
 * The calendar week starts with sunday.
 * The calendar month starts with zero.
 */

internal val Calendar.maxMonth get() = this.getActualMaximum(Calendar.MONTH)

internal val Calendar.minMonth get() = this.getActualMinimum(Calendar.MONTH)

internal val Calendar.maxDay get() = this.getActualMaximum(Calendar.DAY_OF_MONTH)

internal val Calendar.minDay get() = this.getActualMinimum(Calendar.DAY_OF_MONTH)

internal val Calendar.year get() = this.get(Calendar.YEAR)

internal val Calendar.month get() = this.get(Calendar.MONTH)

internal val Calendar.day get() = this.get(Calendar.DAY_OF_MONTH)

internal val Calendar.dayOfWeek get() = this.get(Calendar.DAY_OF_WEEK)
