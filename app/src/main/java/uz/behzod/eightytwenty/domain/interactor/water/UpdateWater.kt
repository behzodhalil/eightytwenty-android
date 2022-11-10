package uz.behzod.eightytwenty.domain.interactor.water

import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity

interface UpdateWater {
    suspend fun updateWater(water: WaterEntity)
}