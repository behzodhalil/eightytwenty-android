package uz.behzod.eightytwenty.domain.interactor.water

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity
import uz.behzod.eightytwenty.domain.repository.WaterRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultUpdateWater @Inject constructor(
    private val waterRepository: WaterRepository,
    private val dispatchers: IDispatcherProvider
): UpdateWater {

    override suspend fun updateWater(water: WaterEntity) {
        return withContext(dispatchers.io) {
            waterRepository.updateWater(water)
        }
    }
}

