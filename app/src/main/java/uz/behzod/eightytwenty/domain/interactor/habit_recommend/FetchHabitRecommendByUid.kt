package uz.behzod.eightytwenty.domain.interactor.habit_recommend

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel


interface FetchHabitRecommendByUid {
    operator fun invoke(uid: Long): Flow<HabitRecommendDomainModel>
}