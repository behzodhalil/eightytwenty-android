package uz.behzod.eightytwenty.domain.interactor.pill

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity
import uz.behzod.eightytwenty.domain.repository.PillRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultFetchPills @Inject constructor(
    private val pillRepository: PillRepository,
    private val dispatchers: IDispatcherProvider
): FetchPills {

    override fun execute(): Flow<List<PillEntity>> {
        return flow {
            pillRepository.fetchPills().collect {
                if (it.isNotEmpty()) {
                    emit(it)
                }
            }
        }.flowOn(dispatchers.io)
    }
}