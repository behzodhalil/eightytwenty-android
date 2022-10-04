package uz.behzod.eightytwenty

import org.junit.Assert
import org.junit.Test
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.utils.constants.BIT_VALUE_OF_FRIDAY
import uz.behzod.eightytwenty.utils.constants.BIT_VALUE_OF_MONDAY
import uz.behzod.eightytwenty.utils.constants.BIT_VALUE_OF_SUNDAY
import java.time.DayOfWeek

class DaysOfWeekTest {

    @Test
    fun `check days of week with bit value`() {
        val daysAsBit =
            BIT_VALUE_OF_MONDAY + BIT_VALUE_OF_FRIDAY + BIT_VALUE_OF_SUNDAY

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