package uz.behzod.eightytwenty.features.habit_recommend

import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel

sealed class HabitRecommendUIState {
    data class Success(val data: List<HabitRecommendDomainModel>): HabitRecommendUIState()
    data class Failure(val message: String): HabitRecommendUIState()
    object Empty: HabitRecommendUIState()
    object Loading: HabitRecommendUIState()
}
