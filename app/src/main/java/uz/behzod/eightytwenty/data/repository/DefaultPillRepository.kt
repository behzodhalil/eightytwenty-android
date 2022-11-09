package uz.behzod.eightytwenty.data.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.domain.repository.PillRepository
import javax.inject.Inject

class DefaultPillRepository @Inject constructor(
    private val sourceManager: LocalSourceManager
): PillRepository {

    override suspend fun insertPill(pill: PillEntity) {
        return sourceManager.insertPill(pill)
    }

    override suspend fun updatePill(pill: PillEntity) {
        return sourceManager.updatePill(pill)
    }

    override suspend fun deletePill(pill: PillEntity) {
        return sourceManager.deletePill(pill)
    }

    override fun fetchPills(): Flow<List<PillEntity>> {
        return sourceManager.fetchPills()
    }
}