package uz.behzod.eightytwenty.domain.interactor.water

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity
import uz.behzod.eightytwenty.domain.repository.WaterRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultDeleteWater @Inject constructor(
    private val waterRepository: WaterRepository,
    private val dispatchers: IDispatcherProvider
): DeleteWater {

    override suspend fun deleteWater(water: WaterEntity) {
        return withContext(dispatchers.io) {
            waterRepository.deleteWater(water)
        }
    }
}