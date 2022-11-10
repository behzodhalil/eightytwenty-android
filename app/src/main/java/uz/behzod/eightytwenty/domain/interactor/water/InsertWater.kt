package uz.behzod.eightytwenty.domain.interactor.water

import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity

interface InsertWater {
    suspend fun insertWater(water: WaterEntity)
}