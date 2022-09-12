package uz.behzod.eightytwenty.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.HabitRecommendEntity

@Dao
interface HabitRecommendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitRecommend(habitRecommend: HabitRecommendEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitRecommends(list: List<HabitRecommendEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHabitRecommend(habitRecommend: HabitRecommendEntity)

    @Delete
    suspend fun deleteHabitRecommend(habitRecommend: HabitRecommendEntity)

    @Query("SELECT * FROM habit_recommend_table WHERE category =:category")
    fun fetchHabitRecommendsByCategory(category: String): Flow<List<HabitRecommendEntity>>

    @Query("SELECT * FROM habit_recommend_table WHERE uid =:uid")
    fun fetchHabitRecommendByUid(uid: Long): Flow<HabitRecommendEntity>
}