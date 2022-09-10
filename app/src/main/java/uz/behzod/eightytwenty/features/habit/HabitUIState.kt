package uz.behzod.eightytwenty.features.habit

import uz.behzod.eightytwenty.domain.model.HabitDomainModel

sealed class HabitUIState {
    data class Success(val data: List<HabitDomainModel>): HabitUIState()
    data class Failure(val message: String): HabitUIState()
    object Empty: HabitUIState()
    object Loading: HabitUIState()
}
