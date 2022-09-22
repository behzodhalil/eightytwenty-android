package uz.behzod.eightytwenty.domain.interactor.task

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.domain.repository.TaskRepository
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class InsertTaskImpl @Inject constructor(
    private val repository: TaskRepository,
    private val noteRepository: NoteRepository,
    private val dispatchers: IDispatcherProvider
) : InsertTask {

    override suspend fun invoke(task: TaskEntity, list: List<NoteDomainModel>) {
        return withContext(dispatchers.io) {
            val data = repository.insertTask(task)
            list.forEach {
                noteRepository.insertNote(it.copy(taskUid = data).asEntity())
            }
        }
    }
}