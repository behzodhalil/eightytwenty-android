package uz.behzod.eightytwenty.domain.interactor.habit_recommend

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.asDomain
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel
import uz.behzod.eightytwenty.domain.repository.HabitRecommendRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchHabitRecommendsByCategoryImpl @Inject constructor(
    private val repository: HabitRecommendRepository,
    private val dispatcherProvider: IDispatcherProvider
): FetchHabitRecommendsByCategory {

    override fun invoke(category: String): Flow<List<HabitRecommendDomainModel>> {
        return flow {
            repository.fetchHabitRecommendsByCategory(category).collect {
                if (it.isNotEmpty()) {
                    emit(it.map { data -> data.asDomain() })
                }
            }
        }.flowOn(dispatcherProvider.io)
    }
}