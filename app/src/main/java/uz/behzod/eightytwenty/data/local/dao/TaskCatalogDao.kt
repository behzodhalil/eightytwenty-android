package uz.behzod.eightytwenty.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.CatalogAndTasks
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity

@Dao
interface TaskCatalogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskCatalog(taskCatalog: TaskCatalogEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTaskCatalog(taskCatalog: TaskCatalogEntity)

    @Delete
    suspend fun deleteTaskCatalog(taskCatalog: TaskCatalogEntity)

    @Query("UPDATE task_catalog_table_name SET task_count = task_count + 1 WHERE catalog_uid =:catalogUid")
    suspend fun incrementTaskCount(catalogUid: Long)

    @Query("UPDATE task_catalog_table_name SET task_count = task_count - 1 WHERE catalog_uid =:catalogUid")
    suspend fun decrementTaskCount(catalogUid: Long)

    @Query("SELECT * FROM task_catalog_table_name ORDER BY catalog_name")
    fun fetchTaskCatalogs(): Flow<List<TaskCatalogEntity>>

    @Query("SELECT * FROM task_catalog_table_name ORDER BY catalog_name, catalog_uid <>1")
    fun fetchTaskAndCatalogs(): Flow<List<CatalogAndTasks>>
}