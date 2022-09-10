package uz.behzod.eightytwenty.domain.interactor.habit

import uz.behzod.eightytwenty.domain.model.HabitDomainModel

interface DeleteHabit{
    suspend operator fun invoke(habit: HabitDomainModel)
}