package uz.behzod.eightytwenty.data.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.domain.repository.WaterRepository
import javax.inject.Inject

class DefaultWaterRepository @Inject constructor(
    private val sourceManager: LocalSourceManager
): WaterRepository {

    override suspend fun insertWater(water: WaterEntity): Long {
        return sourceManager.insertWater(water)
    }

    override suspend fun updateWater(water: WaterEntity) {
        return sourceManager.updateWater(water)
    }

    override fun fetchWaterAfterTimestamp(timestamp: Long): Flow<List<WaterEntity>> {
        return sourceManager.fetchWaterAfterTimestamp(timestamp)
    }

    override fun fetchWaters(): Flow<List<WaterEntity>> {
        return sourceManager.fetchWaters()
    }

    override suspend fun deleteWater(water: WaterEntity) {
        return sourceManager.deleteWater(water)
    }
}