package uz.behzod.eightytwenty.domain.interactor.habit_recommend

import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel

interface UpdateHabitRecommend {
    suspend operator fun invoke(params: HabitRecommendDomainModel)
}