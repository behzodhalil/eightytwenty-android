package uz.behzod.eightytwenty.data.local.db

import androidx.room.TypeConverter
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ZonedDateTimeConverter {
    @TypeConverter
    fun fromZonedDateTime(dateTime: ZonedDateTime?): String? {
        val formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault())
        return if (dateTime != null) formatter.format(dateTime) else null
    }

    @TypeConverter
    fun toZonedDateTime(value: String?): ZonedDateTime? {
        val formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault())
        return if (value != null) ZonedDateTime.parse(value, formatter) else null
    }

    companion object {
        private const val pattern = "dd/MM/yyyy HH:mm:ss"
    }
}
