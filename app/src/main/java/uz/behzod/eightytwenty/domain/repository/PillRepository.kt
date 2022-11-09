package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity

interface PillRepository {
    suspend fun insertPill(pill: PillEntity)
    suspend fun updatePill(pill: PillEntity)
    suspend fun deletePill(pill: PillEntity)
    fun fetchPills(): Flow<List<PillEntity>>
}