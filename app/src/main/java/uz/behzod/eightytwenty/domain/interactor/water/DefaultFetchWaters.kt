package uz.behzod.eightytwenty.domain.interactor.water

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.reminder.WaterEntity
import uz.behzod.eightytwenty.domain.repository.WaterRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultFetchWaters @Inject constructor(
    private val waterRepository: WaterRepository,
    private val dispatchers: IDispatcherProvider,
) : FetchWaters {

    override fun fetchWaters(): Flow<List<WaterEntity>> {
        return flow {
            waterRepository.fetchWaters().collect {
                if (it.isNotEmpty()) {
                    emit(it)
                }
            }
        }.flowOn(dispatchers.io)
    }
}