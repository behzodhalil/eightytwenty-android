package uz.behzod.eightytwenty.domain.interactor.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.domain.repository.TaskRepository
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultFetchTasksNearTime @Inject constructor(
    private val taskRepository: TaskRepository,
    private val dispatchers: IDispatcherProvider
): FetchTasksNearTime {

    override fun execute(): Flow<List<TaskEntity>> {
        return flow {
            taskRepository.fetchTasksNearTime().collect { task ->
                if (task.isNotEmpty()) {
                    printDebug {  "Near time tasks: $task" }
                    emit(task)
                }
            }
        }.flowOn(dispatchers.io)
    }
}