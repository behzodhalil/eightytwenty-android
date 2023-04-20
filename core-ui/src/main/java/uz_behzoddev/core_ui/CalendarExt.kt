package uz.behzoddev.core

import java.util.Calendar

val Calendar.year get() = this.get(Calendar.YEAR)

val Calendar.month get() = this.get(Calendar.MONTH)

val Calendar.day get() = this.get(Calendar.DAY_OF_MONTH)