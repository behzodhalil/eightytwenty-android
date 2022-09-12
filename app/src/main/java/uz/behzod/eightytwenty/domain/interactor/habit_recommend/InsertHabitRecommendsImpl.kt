package uz.behzod.eightytwenty.domain.interactor.habit_recommend

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.HabitRecommendEntity
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.model.HabitRecommendDomainModel
import uz.behzod.eightytwenty.domain.repository.HabitRecommendRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class InsertHabitRecommendsImpl @Inject constructor(
    private val repository: HabitRecommendRepository,
    private val dispatcherProvider: IDispatcherProvider
) : InsertHabitRecommends {

    override suspend fun invoke(list: List<HabitRecommendDomainModel>) {
        withContext(dispatcherProvider.io) {
            repository.insertHabitRecommends(list.map { it.asEntity() })
        }
    }
}