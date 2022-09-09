package uz.behzod.eightytwenty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import uz.behzod.eightytwenty.utils.ext.Empty
import uz.behzod.eightytwenty.utils.ext.Zero
import java.time.ZonedDateTime

@Parcelize
data class NoteDomainModel(
    val id: Long = Long.Zero,
    val title: String = String.Empty,
    val description: String = String.Empty,
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
    val isTrashed: Boolean = false,
    val categoryId: Long = Long.Zero
) : Parcelable
