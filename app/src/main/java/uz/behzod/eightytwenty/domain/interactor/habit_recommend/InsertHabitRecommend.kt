package uz.behzod.eightytwenty.domain.interactor.habit_recommend

import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel

interface InsertHabitRecommend {
    suspend operator fun invoke(value: HabitRecommendDomainModel)
}