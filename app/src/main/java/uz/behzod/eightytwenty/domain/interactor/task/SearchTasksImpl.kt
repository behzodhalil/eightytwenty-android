package uz.behzod.eightytwenty.domain.interactor.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.domain.repository.TaskRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class SearchTasksImpl @Inject constructor(
    private val repository: TaskRepository,
    private val dispatcherProvider: IDispatcherProvider
): SearchTasks {

    override fun invoke(taskName: String): Flow<List<TaskEntity>> {
        return  flow {
            repository.searchTasks(taskName).collect {
                if(it.isNotEmpty()) {
                  emit(it)
                }
            }
        }.flowOn(dispatcherProvider.io)
    }
}