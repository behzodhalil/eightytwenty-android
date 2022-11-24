package uz.behzod.eightytwenty.features.add_bill

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

/**
 * It represents the state of
 */
data class AddBillState(
    val name: String = String.Empty,
    val amount: Long = Long.Zero,
    val timestamp: ZonedDateTime? = null,
    val biller: String = String.Empty,
    val isDatePicked: Boolean = false,
    val isSuccess: Boolean = false,
    val isSaved: Boolean = false,
    val isSaveFailed: Boolean = false

): ViewState {
    companion object {
        /**
         * It represents an empty state of [AddBillState].
         */
        val empty = AddBillState()
    }
}
