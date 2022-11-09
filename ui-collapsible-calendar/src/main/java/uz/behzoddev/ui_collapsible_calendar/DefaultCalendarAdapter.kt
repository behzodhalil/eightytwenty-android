package uz.behzoddev.ui_collapsible_calendar

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import uz.behzoddev.R
import uz.behzoddev.ui_collapsible_calendar.utils.*
import uz.behzoddev.ui_collapsible_calendar.utils.dayOfWeek
import uz.behzoddev.ui_collapsible_calendar.utils.maxDay
import uz.behzoddev.ui_collapsible_calendar.utils.month
import uz.behzoddev.ui_collapsible_calendar.utils.year
import java.util.*
import kotlin.math.ceil

class DefaultCalendarAdapter(context: Context,cal: Calendar) {

    var calendar: Calendar = cal.clone() as Calendar
    private val days = arrayListOf<Day>()
    private val views = arrayListOf<View>()
    private val events = arrayListOf<Event>()
    private val mInflater: LayoutInflater
    private var firstDay = 0

    val count: Int get() = days.size

    val year: Int get() = calendar.year
    val month: Int get() = calendar.month
    val day: Int get() = calendar.day
    val timezone: TimeZone get() = calendar.timeZone
    val time: Date get() = calendar.time
    init {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        mInflater = LayoutInflater.from(context)
    }

    fun view(position: Int): View {
        return views[position]
    }

    fun day(position: Int): Day {
        return days[position]
    }

    fun firstDayOfWeek(day: Int) {
        firstDay = day
    }

    fun refresh() {
        clear()
        val year = calendar.year
        val month = calendar.month

        calendar.set(year, month, 1)

        val lastDayOfMonth = calendar.maxDay
        val firstDayOfWeek = calendar.dayOfWeek - 1

        // Distance (displacement) between the beginning of the object and a given element or point.
        val offset = 0 - (firstDayOfWeek - firstDay) + 1
        val length = ceil(day = lastDayOfMonth, offset = offset)
        val sum = length + offset

        for (i in offset until sum) {
            val numYear: Int
            val numMonth: Int
            val numDay: Int

            val tempCal = Calendar.getInstance()
            when {
                i <= 0 -> { // prev month
                    if (month == 0) {
                        numYear = year - 1
                        numMonth = 11
                    } else {
                        numYear = year
                        numMonth = month - 1
                    }
                    tempCal.set(numYear, numMonth, 1)
                    numDay = tempCal.maxDay + i
                }
                i > lastDayOfMonth -> { // next month
                    if (month == 11) {
                        numYear = year + 1
                        numMonth = 0
                    } else {
                        numYear = year
                        numMonth = month + 1
                    }
                    tempCal.set(numYear, numMonth, 1)
                    numDay = i - lastDayOfMonth
                }
                else -> {
                    numYear = year
                    numMonth = month
                    numDay = i
                }
            }

            val day = Day(numYear, numMonth, numDay)

            val view = mInflater.inflate(R.layout.view_holder_day, null)
            val txtDay = view.findViewById<View>(R.id.tv_day) as TextView
            val ivTag = view.findViewById<View>(R.id.iv_tag) as ImageView

            txtDay.text = day.day.toString()
            if (day.month != calendar.get(Calendar.MONTH)) {
                txtDay.alpha = 0.3f
            }

            for (j in events.indices) {
                val event = events[j]
                if (day.year == event.year
                    && day.month == event.month
                    && day.day == event.day
                ) {
                    ivTag.visibility = View.VISIBLE
                    ivTag.setColorFilter(event.color, PorterDuff.Mode.SRC_ATOP)
//                    imgEventTag.visibility = View.VISIBLE
//                    imgEventTag.setColorFilter(event.color, PorterDuff.Mode.SRC_ATOP)
                }
            }
            add(view, day)
        }

    }

    private fun clear() {
        days.clear()
        views.clear()
    }

    private fun add(view: View, day: Day) {
        days.add(element = day)
        views.add(element = view)
    }

    private fun ceil(day: Int, offset: Int): Int {
        return ceil(((day - offset + 1).toFloat() / 7).toDouble()).toInt() * 7
    }

}