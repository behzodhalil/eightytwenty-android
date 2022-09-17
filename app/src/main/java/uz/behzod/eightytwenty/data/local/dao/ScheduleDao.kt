package uz.behzod.eightytwenty.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity

@Dao
interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: ScheduleEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSchedule(schedule: ScheduleEntity)

    @Query("DELETE FROM schedule_table WHERE schedule_habit_uid = :taskUid")
    suspend fun deleteByUid(taskUid: Long)

    @Query("DELETE FROM schedule_table WHERE schedule_habit_uid =:habitUid")
    suspend fun deleteByHabitUid(habitUid: Long)

    @Query("SELECT * FROM schedule_table")
    fun fetchSchedules(): Flow<List<ScheduleEntity>>

    @Query("SELECT * FROM schedule_table WHERE schedule_uid =:uid")
    fun fetchSchedulesByUid(uid: Long): Flow<ScheduleEntity>
}