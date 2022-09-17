package uz.behzod.eightytwenty.domain.interactor.habit

import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.domain.model.HabitDomainModel

interface InsertHabit {
    suspend operator fun invoke(habit: HabitDomainModel, scheduleEntity: List<ScheduleEntity>)
}