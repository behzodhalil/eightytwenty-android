package uz.behzod.eightytwenty.domain.interactor.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.domain.repository.TaskRepository
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultFetchTasksRecent @Inject constructor(
    private val taskRepository: TaskRepository,
    private val dispatchers: IDispatcherProvider,
) : FetchTasksRecent {

    override fun execute(): Flow<List<TaskEntity>> {
        return flow {
            taskRepository.fetchTasksRecent().collect { task ->
                if (task.isNotEmpty()) {
                    printDebug { "Recent tasks: $task" }
                    emit(task)
                }
            }
        }.flowOn(dispatchers.io)
    }
}