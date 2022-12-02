package uz.behzod.eightytwenty.features.add_water

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

data class AddWaterState(
    val quantity: Long = Long.Zero,
    val frequency: String = String.Empty,
    val reminderTime: ZonedDateTime? = null,
    val isDatePicked: Boolean = false,
    val isSaved: Boolean = false,
    val isSaveFailed: Boolean  = false
): ViewState
