package uz.behzod.eightytwenty.domain.interactor.habit

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.domain.model.HabitDomainModel

interface FetchAllHabits {
    operator fun invoke(): Flow<List<HabitDomainModel>>
}