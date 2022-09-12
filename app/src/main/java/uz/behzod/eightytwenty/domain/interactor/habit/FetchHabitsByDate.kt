package uz.behzod.eightytwenty.domain.interactor.habit

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.domain.model.HabitDomainModel

interface FetchHabitsByDate {
    operator fun invoke(timestamp: String): Flow<List<HabitDomainModel>>
}