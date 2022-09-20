package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.CatalogAndTasks
import uz.behzod.eightytwenty.data.local.entities.TaskCatalogEntity

interface TaskCatalogRepository {
    suspend fun insertTaskCatalog(taskCatalog: TaskCatalogEntity)
    suspend fun updateTaskCatalog(taskCatalog: TaskCatalogEntity)
    suspend fun deleteTaskCatalog(taskCatalog: TaskCatalogEntity)
    suspend fun incrementTaskCount(catalogUid: Long)
    suspend fun decrementTaskCount(catalogUid: Long)
    fun fetchTaskCatalogs(): Flow<List<TaskCatalogEntity>>
    fun fetchTaskAndCatalogs(): Flow<List<CatalogAndTasks>>
    fun searchCatalog(catalogName: String): Flow<List<TaskCatalogEntity>>
}