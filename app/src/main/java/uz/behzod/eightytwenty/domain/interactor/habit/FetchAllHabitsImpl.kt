package uz.behzod.eightytwenty.domain.interactor.habit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.asDomain
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import uz.behzod.eightytwenty.domain.repository.HabitRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchAllHabitsImpl @Inject constructor(
    private val repository: HabitRepository,
    private val dispatcherProvider: IDispatcherProvider
): FetchAllHabits {

    override fun invoke(): Flow<List<HabitDomainModel>> {
        return flow {
            repository.fetchAllHabits().collect {
                if (it.isEmpty()) {
                    emit(it.map { habit -> habit.asDomain() })
                }
            }
        }.flowOn(dispatcherProvider.io)
    }
}