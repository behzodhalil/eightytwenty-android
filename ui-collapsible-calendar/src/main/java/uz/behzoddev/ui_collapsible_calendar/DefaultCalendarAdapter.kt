package uz.behzoddev.ui_collapsible_calendar

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import java.util.*
import kotlin.math.ceil

class DefaultCalendarAdapter(context: Context) {

    private val calendar = Calendar.getInstance()
    private val days = arrayListOf<Day>()
    private val views = arrayListOf<View>()
    private val events = arrayListOf<Event>()

    init {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
    }

    fun view(position: Int): View {
        return views[position]
    }

    fun day(position: Int): Day {
        return days[position]
    }

    fun refresh() {
        clear()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)

        calendar.set(year, month, 1)

        val lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1

        val offset = 0 - (firstDayOfWeek - 0) + 1
        val length = ceil(((lastDayOfMonth - offset + 1).toFloat() / 7).toDouble()).toInt() * 7

        for (i in offset until length + offset) {
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
                    numDay = tempCal.getActualMaximum(Calendar.DAY_OF_MONTH) + i
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

//            val view = mInflater.inflate(R.layout.day_layout, null)
//            val txtDay = view.findViewById<View>(R.id.txt_day) as TextView
//            val imgEventTag = view.findViewById<View>(R.id.img_event_tag) as ImageView

//            txtDay.text = day.day.toString()
//            if (day.month != calendar.get(Calendar.MONTH)) {
//                txtDay.alpha = 0.3f
//            }

            for (j in events.indices) {
                val event = events[j]
                if (day.year == event.year
                    && day.month == event.month
                    && day.day == event.day
                ) {
//                    imgEventTag.visibility = View.VISIBLE
//                    imgEventTag.setColorFilter(event.color, PorterDuff.Mode.SRC_ATOP)
                }
            }
        }
    }

    private fun clear() {
        days.clear()
        views.clear()
    }

    private fun check() {

    }

    private fun add(view: View, day: Day) {
        days.add(element = day)
        views.add(element = view)
    }

    private fun createDay() {}

}