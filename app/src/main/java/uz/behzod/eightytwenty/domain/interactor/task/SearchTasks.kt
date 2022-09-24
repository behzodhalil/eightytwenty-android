package uz.behzod.eightytwenty.domain.interactor.task

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.TaskEntity

interface SearchTasks {
    operator fun invoke(taskName: String): Flow<List<TaskEntity>>
}