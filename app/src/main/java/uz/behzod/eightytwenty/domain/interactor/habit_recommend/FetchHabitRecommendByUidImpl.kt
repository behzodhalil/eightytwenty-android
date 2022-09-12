package uz.behzod.eightytwenty.domain.interactor.habit_recommend

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.asDomain
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel
import uz.behzod.eightytwenty.domain.repository.HabitRecommendRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class FetchHabitRecommendByUidImpl @Inject constructor(
    private val repository: HabitRecommendRepository,
    private val dispatcherProvider: IDispatcherProvider
): FetchHabitRecommendByUid {

    override fun invoke(uid: Long): Flow<HabitRecommendDomainModel> {
        return flow {
            repository.fetchHabitRecommendByUid(uid).collect {
                emit(it.asDomain())
            }
        }.flowOn(dispatcherProvider.io)
    }
}