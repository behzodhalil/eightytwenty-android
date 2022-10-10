package uz.behzod.eightytwenty.features.habit_recommend

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.data.local.entities.HabitRecommendEntity
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel
import uz.behzod.eightytwenty.utils.extension.Empty

data class HabitRecommendState(
    val habits: List<HabitRecommendDomainModel> = emptyList(),
    val category: String = String.Empty,
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val isEmpty: Boolean = false
): ViewState
