package uz.behzod.eightytwenty.data.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.domain.repository.ScheduleRepository
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val sourceManager: LocalSourceManager
): ScheduleRepository {

    override suspend fun insertSchedule(schedule: ScheduleEntity) {
        return sourceManager.insertSchedule(schedule)
    }

    override suspend fun updateSchedule(schedule: ScheduleEntity) {
       return sourceManager.updateSchedule(schedule)
    }

    override suspend fun deleteByUid(taskUid: Long) {
        return sourceManager.deleteByUid(taskUid)
    }

    override fun fetchSchedules(): Flow<List<ScheduleEntity>> {
        return sourceManager.fetchSchedules()
    }

    override fun fetchSchedulesByUid(uid: Long): Flow<ScheduleEntity> {
        return sourceManager.fetchSchedulesByUid(uid)
    }
}