package uz.behzod.eightytwenty.domain.interactor.habit

import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import javax.inject.Inject

class InsertHabitImpl @Inject constructor(

): InsertHabit {

    override suspend fun invoke(habit: HabitDomainModel) {
        TODO("Not yet implemented")
    }
}