package uz.behzod.eightytwenty.domain.interactor.habit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.asDomain
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.domain.repository.HabitRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchHabitsByDateImpl @Inject constructor(
    private val repository: HabitRepository,
    private val dispatcherProvider: IDispatcherProvider
): FetchHabitsByDate {

    override fun invoke(timestamp: String): Flow<List<HabitDomainModel>> {
        return flow {
            repository.fetchHabitsByDate(timestamp).collect { result ->
                if (result.isNotEmpty()) {
                    emit(result.map { it.asDomain() })
                } else {
                    emit(emptyList<HabitDomainModel>())
                }
            }
        }.flowOn(dispatcherProvider.io)
    }
}