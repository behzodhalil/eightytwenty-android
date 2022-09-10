package uz.behzod.eightytwenty.domain.interactor.habit

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.domain.repository.HabitRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class UpdateHabitImpl @Inject constructor(
   private val repository: HabitRepository,
   private val dispatcherProvider: IDispatcherProvider
):UpdateHabit {

    override suspend fun invoke(habit: HabitDomainModel) {
        return withContext(dispatcherProvider.io) {
            repository.updateHabit(habit.asEntity())
        }
    }
}