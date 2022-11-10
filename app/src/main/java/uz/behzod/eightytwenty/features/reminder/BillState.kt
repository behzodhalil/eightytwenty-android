package uz.behzod.eightytwenty.features.reminder

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity

data class BillState(
    val bills: List<BillEntity> = emptyList(),
    val isSuccess: Boolean = false,
    val isLoaded: Boolean = false,
    val isEmpty: Boolean = false
): ViewState
