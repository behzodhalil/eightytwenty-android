package uz.behzod.eightytwenty.domain.interactor.habit

import android.util.Log
import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.dao.HabitDao
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.domain.repository.HabitRepository
import uz.behzod.eightytwenty.domain.repository.ScheduleRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class InsertHabitImpl @Inject constructor(
    private val repository: HabitRepository,
    private val habitDao: HabitDao,
    private val scheduleRepository: ScheduleRepository,
    private val dispatcherProvider: IDispatcherProvider
) : InsertHabit {

    override suspend fun invoke(habit: HabitDomainModel, scheduleEntity: List<ScheduleEntity>) {
        return withContext(dispatcherProvider.io) {
            val data = habitDao.insertHabit(habit.asEntity())
            scheduleEntity.forEach {
                scheduleRepository.insertSchedule(it.copy(habitId = data))
            }
            Log.d("InsertHabitImpl", "Inserted schedule is $scheduleEntity")
        }
    }
}