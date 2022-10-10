package uz.behzod.eightytwenty.features.habit_detail

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.utils.extension.Zero

data class HabitDetailState(
    val habit: HabitDomainModel? = null,
    val uid: Long = Long.Zero,
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false
): ViewState
