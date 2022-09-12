package uz.behzod.eightytwenty.data.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.HabitRecommendEntity
import uz.behzod.eightytwenty.data.source.LocalSourceManager
import uz.behzod.eightytwenty.domain.repository.HabitRecommendRepository
import javax.inject.Inject

class HabitRecommendRepositoryImpl @Inject constructor(
    private val sourceManager: LocalSourceManager
) : HabitRecommendRepository {

    override suspend fun insertHabitRecommend(habitRecommend: HabitRecommendEntity) {
        return sourceManager.insertHabitRecommend(habitRecommend)
    }

    override suspend fun insertHabitRecommends(list: List<HabitRecommendEntity>) {
        return sourceManager.insertHabitRecommends(list)
    }

    override suspend fun updateHabitRecommend(habitRecommend: HabitRecommendEntity) {
        return sourceManager.updateHabitRecommend(habitRecommend)
    }

    override suspend fun deleteHabitRecommend(habitRecommend: HabitRecommendEntity) {
        return sourceManager.deleteHabitRecommend(habitRecommend)
    }

    override fun fetchHabitRecommendsByCategory(category: String):Flow<List<HabitRecommendEntity>> {
        return sourceManager.fetchHabitRecommendsByCategory(category)
    }
}