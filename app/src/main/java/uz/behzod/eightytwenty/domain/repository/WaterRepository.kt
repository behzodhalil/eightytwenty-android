package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity

interface WaterRepository {
    suspend fun insertWater(water: WaterEntity): Long
    suspend fun updateWater(water: WaterEntity)
    suspend fun deleteWater(water: WaterEntity)
    fun fetchWaterAfterTimestamp(timestamp: Long): Flow<List<WaterEntity>>
    fun fetchWaters(): Flow<List<WaterEntity>>
}