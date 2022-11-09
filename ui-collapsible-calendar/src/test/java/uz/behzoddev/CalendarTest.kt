package uz.behzoddev

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import uz.behzoddev.ui_collapsible_calendar.utils.*
import java.util.*

class CalendarTest {

    private lateinit var calendar: Calendar

    @Before
    fun setup() {
        calendar = Calendar.getInstance()
    }

    @Test
    fun `check the max of month`() {
        val maxMont = calendar.maxMonth + 1
        val count = 12

        Assert.assertEquals(count, maxMont)
    }

    @Test
    fun `check the min of month`() {
        val minMonth = calendar.minMonth
        val count = 0

        Assert.assertEquals(count, minMonth)
    }

    @Test
    fun `check the max of day on the month`() {
        val maxDay = calendar.maxDay + 1
        val count = 31

        Assert.assertEquals(count, maxDay)
    }

    @Test
    fun `check the min of day on the month`() {
        val maxDay = calendar.minDay
        val count = 1

        Assert.assertEquals(count, maxDay)
    }

    @Test
    fun `check the current year`() {
        val year = calendar.year
        Assert.assertEquals(2022, year)
    }

    @Test
    fun `check the current month`() {
        val month = calendar.month + 1
        Assert.assertEquals(11, month)
    }

    @Test
    fun `check the current day`() {
        val day = calendar.day
        Assert.assertEquals(9, day)
    }

    @Test
    fun `check the day of month`() {
        val dayOfWeek = calendar.dayOfWeek - 1
        Assert.assertEquals(3, dayOfWeek)
    }

    @Test
    fun `check the day of offset`() {
        val dayOfWeek = calendar.dayOfWeek - 1
        val offset = 0 - (dayOfWeek - 0) + 1
        Assert.assertEquals(-2,offset)
    }


}