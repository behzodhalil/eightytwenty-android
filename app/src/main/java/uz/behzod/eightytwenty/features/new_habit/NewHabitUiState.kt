package uz.behzod.eightytwenty.features.new_habit

import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel

sealed class NewHabitUiState {
    data class Success(val data: HabitRecommendDomainModel): NewHabitUiState()
    data class Failure(val message: String): NewHabitUiState()
    object Empty: NewHabitUiState()
    object Loading : NewHabitUiState()
}
