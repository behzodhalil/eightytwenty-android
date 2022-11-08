package uz.behzoddev.ui_collapsible_calendar

import java.util.*

internal val Calendar.maxMonth get() = this.getActualMaximum(Calendar.MONTH)

internal val Calendar.minMonth get() = this.getActualMinimum(Calendar.MONTH)

internal val Calendar.maxDay get() = this.getActualMaximum(Calendar.DAY_OF_MONTH)

internal val Calendar.minDay get() = this.getActualMinimum(Calendar.DAY_OF_MONTH)
