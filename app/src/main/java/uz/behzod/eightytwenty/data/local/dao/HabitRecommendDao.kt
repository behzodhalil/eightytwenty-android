package uz.behzod.eightytwenty.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.HabitRecommendEntity

@Dao
interface HabitRecommendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitRecommend(habitRecommend: HabitRecommendEntity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHabitRecommend(habitRecommend: HabitRecommendEntity)
    @Delete
    suspend fun deleteHabitRecommend(habitRecommend: HabitRecommendEntity)

    @Query("SELECT * FROM habit_recommend_table_name WHERE category LIKE :category")
    fun fetchHabitRecommendsByCategory(category: String): Flow<List<HabitRecommendEntity>>
}