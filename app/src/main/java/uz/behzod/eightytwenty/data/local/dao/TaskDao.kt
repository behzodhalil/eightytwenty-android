package uz.behzod.eightytwenty.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.TaskEntity

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM task_entity_table_name WHERE uid =:uid")
    fun fetchTaskByUid(uid: Long): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task_entity_table_name WHERE task_is_complete <>1 AND task_is_trashed <>1 ORDER BY task_timestamp")
    fun fetchTasks(): Flow<List<TaskEntity>>
}