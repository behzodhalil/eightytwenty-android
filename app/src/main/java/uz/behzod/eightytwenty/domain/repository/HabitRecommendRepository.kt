package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.HabitRecommendEntity

interface HabitRecommendRepository {
    suspend fun insertHabitRecommend(habitRecommend: HabitRecommendEntity)
    suspend fun insertHabitRecommends(list: List<HabitRecommendEntity>)
    suspend fun updateHabitRecommend(habitRecommend: HabitRecommendEntity)
    suspend fun deleteHabitRecommend(habitRecommend: HabitRecommendEntity)
    fun fetchHabitRecommendsByCategory(category: String): Flow<List<HabitRecommendEntity>>
}