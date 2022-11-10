package uz.behzod.eightytwenty.domain.interactor.water

import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity

interface DeleteWater {
    suspend fun deleteWater(water: WaterEntity)
}