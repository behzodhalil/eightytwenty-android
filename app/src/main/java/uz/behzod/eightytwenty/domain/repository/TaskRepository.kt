package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.TaskEntity

interface TaskRepository {
    suspend fun insertTask(task: TaskEntity): Long
    suspend fun updateTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
    fun fetchTaskByUid(uid: Long): Flow<List<TaskEntity>>
    fun fetchTasks(): Flow<List<TaskEntity>>
    fun searchTasks(taskName: String): Flow<List<TaskEntity>>
    fun fetchTasksRecent(): Flow<List<TaskEntity>>
    fun fetchTasksNearTime(): Flow<List<TaskEntity>>
    fun fetchLimitedTasks(): Flow<List<TaskEntity>>
    fun fetchTasksByFolderUid(folderUid: Long): Flow<List<TaskEntity>>
    fun fetchCompletedTasksByFolderUid(folderUid: Long): Flow<List<TaskEntity>>
}
