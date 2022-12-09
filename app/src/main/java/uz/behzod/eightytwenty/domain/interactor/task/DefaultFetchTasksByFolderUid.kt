package uz.behzod.eightytwenty.domain.interactor.task

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.domain.repository.TaskRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class DefaultFetchTasksByFolderUid @Inject constructor(
    private val taskRepository: TaskRepository,
    private val dispatcher: IDispatcherProvider
): FetchTasksByFolderUid {

    override fun execute(folderUid: Long): Flow<List<TaskEntity>> {
        return flow {
            taskRepository.fetchTasksByFolderUid(folderUid).collect {
                if (it.isNotEmpty()) {
                    emit(it)
                }
            }
        }.flowOn(dispatcher.io)
    }
}
