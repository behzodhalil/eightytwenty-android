package uz.behzod.eightytwenty.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.TaskEntity

interface TaskRepository {
    suspend fun insertTask(task: TaskEntity)
    suspend fun updateTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
    fun fetchTaskByUid(uid: Long): Flow<List<TaskEntity>>
    fun fetchTasks(): Flow<List<TaskEntity>>
}