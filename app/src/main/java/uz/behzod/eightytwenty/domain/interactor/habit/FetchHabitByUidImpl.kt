package uz.behzod.eightytwenty.domain.interactor.habit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.asDomain
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.domain.repository.HabitRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchHabitByUidImpl @Inject constructor(
    private val repository: HabitRepository,
    private val dispatcherProvider: IDispatcherProvider
) : FetchHabitByUid {

    override fun invoke(uid: Long): Flow<HabitDomainModel> {
        return flow {
            repository.fetchHabitByUid(uid).collect {
                emit(it.asDomain())
            }
        }.flowOn(dispatcherProvider.io)
    }
}