package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity

interface ScheduleRepository {
    suspend fun insertSchedule(schedule: ScheduleEntity)
    suspend fun updateSchedule(schedule: ScheduleEntity)
    suspend fun deleteByUid(taskUid: Long)
    fun fetchSchedules(): Flow<List<ScheduleEntity>>
    fun fetchSchedulesByUid(uid: Long): Flow<ScheduleEntity>
}