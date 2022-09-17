package uz.behzod.eightytwenty

import org.junit.Assert
import org.junit.Test
import uz.behzod.eightytwenty.utils.extension.isToday
import java.time.ZonedDateTime

class ZonedDateTimeTest {

    @Test
    fun `check zone date time with today`() {
        val timestamp = ZonedDateTime.now()

        Assert.assertTrue(timestamp.isToday())
    }
}