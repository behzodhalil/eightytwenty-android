package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.HabitEntity

interface HabitRepository {
    suspend fun insertHabit(habit: HabitEntity)
    suspend fun updateHabit(habit: HabitEntity)
    suspend fun deleteHabit(habit: HabitEntity)
    fun fetchHabitByUid(uid: Long): Flow<HabitEntity>
    fun fetchAllHabits(): Flow<List<HabitEntity>>
    fun fetchHabitsByDate(timestamp: Long): Flow<List<HabitEntity>>
}