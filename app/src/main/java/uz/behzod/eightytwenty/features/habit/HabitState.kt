package uz.behzod.eightytwenty.features.habit

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.utils.extension.Empty

data class HabitState(
    val habits: List<HabitDomainModel> = emptyList(),
    val timestamp: String = String.Empty,
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false
): ViewState
