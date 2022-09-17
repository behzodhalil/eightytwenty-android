package uz.behzod.eightytwenty

import org.junit.Assert
import org.junit.Test
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import java.time.DayOfWeek

class DaysOfWeekTest {

    @Test
    fun `check days of week with bit value`() {
        val daysAsBit =
            ScheduleEntity.BIT_VALUE_OF_MONDAY + ScheduleEntity.BIT_VALUE_OF_FRIDAY + ScheduleEntity.BIT_VALUE_OF_SUNDAY

        Assert.assertTrue(
            ScheduleEntity.parseDaysOfWeek(daysAsBit).contains(DayOfWeek.MONDAY.value)
        )
        Assert.assertTrue(
            ScheduleEntity.parseDaysOfWeek(daysAsBit).contains(DayOfWeek.FRIDAY.value)
        )
        Assert.assertTrue(
            ScheduleEntity.parseDaysOfWeek(daysAsBit).contains(DayOfWeek.SUNDAY.value)
        )
    }
}