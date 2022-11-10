package uz.behzod.eightytwenty.domain.interactor.water

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity

interface FetchWaterAfterTimestamp {
    fun execute(timestamp: String): Flow<List<WaterEntity>>
}