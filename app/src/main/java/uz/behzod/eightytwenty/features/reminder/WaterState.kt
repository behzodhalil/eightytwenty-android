package uz.behzod.eightytwenty.features.reminder

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity

data class WaterState(
    val waters: List<WaterEntity> = emptyList(),
    val isSuccess: Boolean = false,
    val isFailed: Boolean = false,
    val isEmpty: Boolean = false,
    val isLoading: Boolean = false
): ViewState
