package uz.behzod.eightytwenty.data.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.HabitEntity
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.domain.repository.HabitRepository
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val sourceManager: LocalSourceManager
): HabitRepository {

    override suspend fun insertHabit(habit: HabitEntity) {
        return sourceManager.insertHabit(habit)
    }

    override suspend fun updateHabit(habit: HabitEntity) {
        return sourceManager.updateHabit(habit)
    }

    override suspend fun deleteHabit(habit: HabitEntity) {
        return sourceManager.deleteHabit(habit)
    }

    override fun fetchHabitByUid(uid: Long): Flow<HabitEntity> {
        return sourceManager.fetchHabitByUid(uid)
    }

    override fun fetchAllHabits(): Flow<List<HabitEntity>> {
        return sourceManager.fetchAllHabits()
    }

    override fun fetchHabitsByDate(timestamp: Long): Flow<List<HabitEntity>> {
        return sourceManager.fetchHabitsByDate(timestamp)
    }
}