package uz.behzod.eightytwenty.domain.interactor.water

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity

interface FetchWaters {
    fun execute(): Flow<List<WaterEntity>>
}
