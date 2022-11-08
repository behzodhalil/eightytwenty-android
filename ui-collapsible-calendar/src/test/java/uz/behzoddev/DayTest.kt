package uz.behzoddev

import dalvik.annotation.TestTarget
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import uz.behzoddev.ui_collapsible_calendar.Day
import uz.behzoddev.ui_collapsible_calendar.toUnixTime
import java.util.*

class DayTest {

    private lateinit var calendar: Calendar
    private lateinit var day: Day

    @Before
    fun setup() {
        calendar = Calendar.getInstance()
        day = Day(
            year = calendar[Calendar.YEAR],
            month = calendar[Calendar.MONTH],
            day = calendar[Calendar.DAY_OF_MONTH])
    }

    @Test
    fun `check_year_of_day`() {
        val year = calendar.get(Calendar.YEAR)
        Assert.assertEquals(year, day.year)
    }

    @Test
    fun `check_month_of_day`() {
        val month = calendar.get(Calendar.MONTH)
        Assert.assertEquals(month, day.month)
    }

    @Test
    fun `check_day_of_month`() {
        val mDay = calendar.get(Calendar.DAY_OF_MONTH)
        Assert.assertEquals(mDay,day.day)
    }

    @Test
    fun `check_date_to_unit_time`() {
        val date = Date(
            day.year,
            day.month,
            day.day
        )
        Assert.assertEquals(date.time,day.toUnixTime())
    }

}