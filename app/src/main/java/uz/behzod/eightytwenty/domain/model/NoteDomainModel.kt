package uz.behzod.eightytwenty.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

@Parcelize
data class NoteDomainModel(
    val id: Long = Long.Zero,
    var title: String = String.Empty,
    var description: String = String.Empty,
    var timestamp: ZonedDateTime = ZonedDateTime.now(),
    val isTrashed: Boolean = false,
    val categoryId: Long = Long.Zero,
    val taskUid: Long? = null
) : Parcelable
