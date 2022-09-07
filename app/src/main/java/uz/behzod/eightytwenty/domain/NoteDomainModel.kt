package uz.behzod.eightytwenty.domain

import uz.behzod.eightytwenty.utils.ext.Empty
import uz.behzod.eightytwenty.utils.ext.Zero
import java.time.ZonedDateTime

data class NoteDomainModel(
    val id: Long = Long.Zero,
    val title: String = String.Empty,
    val description: String = String.Empty,
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
    val isTrashed: Boolean = false,
    val categoryId: Long = Long.Zero
)
