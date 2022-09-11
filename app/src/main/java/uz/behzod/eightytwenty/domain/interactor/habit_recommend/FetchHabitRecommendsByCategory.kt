package uz.behzod.eightytwenty.domain.interactor.habit_recommend

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel

interface FetchHabitRecommendsByCategory {
    operator fun invoke(category: String): Flow<List<HabitRecommendDomainModel>>
}