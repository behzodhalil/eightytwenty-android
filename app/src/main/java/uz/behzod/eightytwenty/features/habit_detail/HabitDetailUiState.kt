package uz.behzod.eightytwenty.features.habit_detail

import uz.behzod.eightytwenty.domain.model.HabitDomainModel

sealed class HabitDetailUiState {
    data class Success(val data: HabitDomainModel): HabitDetailUiState()
    data class Failure(val message: String): HabitDetailUiState()
    object Empty: HabitDetailUiState()
    object Loading: HabitDetailUiState()
}
