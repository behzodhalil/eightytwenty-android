package uz.behzoddev

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import uz.behzoddev.ui_collapsible_calendar.maxDay
import uz.behzoddev.ui_collapsible_calendar.maxMonth
import uz.behzoddev.ui_collapsible_calendar.minDay
import uz.behzoddev.ui_collapsible_calendar.minMonth
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

        Assert.assertEquals(count,maxMont)
    }

    @Test
    fun `check the min of month`() {
        val minMonth = calendar.minMonth
        val count = 0

        Assert.assertEquals(count,minMonth)
    }

    @Test
    fun `check the max of day on the month`() {
        val maxDay = calendar.maxDay + 1
        val count = 31

        Assert.assertEquals(count,maxDay)
    }

    @Test
    fun `check the min of day on the month`() {
        val maxDay = calendar.minDay
        val count = 1

        Assert.assertEquals(count,maxDay)
    }
}