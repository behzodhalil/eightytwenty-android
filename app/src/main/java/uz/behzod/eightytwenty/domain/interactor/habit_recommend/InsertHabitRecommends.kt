package uz.behzod.eightytwenty.domain.interactor.habit_recommend

import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel

interface InsertHabitRecommends {
    suspend operator fun invoke(list: List<HabitRecommendDomainModel>)
}