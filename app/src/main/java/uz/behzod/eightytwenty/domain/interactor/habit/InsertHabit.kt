package uz.behzod.eightytwenty.domain.interactor.habit

import uz.behzod.eightytwenty.domain.model.HabitDomainModel

interface InsertHabit {
    suspend operator fun invoke(habit: HabitDomainModel)
}