package uz.behzod.eightytwenty.domain.interactor.water

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity
import uz.behzod.eightytwenty.domain.repository.WaterRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultInsertWater @Inject constructor(
    private val waterRepository: WaterRepository,
    private val dispatchers: IDispatcherProvider
): InsertWater {

    override suspend fun insertWater(water: WaterEntity) {
       return withContext(dispatchers.io) {
           waterRepository.insertWater(water)
       }
    }
}