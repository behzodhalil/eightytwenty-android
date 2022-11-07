package uz.behzod.eightytwenty.domain.interactor.task

import kotlinx.coroutines.withContext
import uz.behzod.eightytwenty.data.local.entities.AttachmentEntity
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.data.local.entities.asEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.domain.repository.AttachmentRepository
import uz.behzod.eightytwenty.domain.repository.NoteRepository
import uz.behzod.eightytwenty.domain.repository.ScheduleRepository
import uz.behzod.eightytwenty.domain.repository.TaskRepository
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

class InsertTaskImpl @Inject constructor(
    private val repository: TaskRepository,
    private val noteRepository: NoteRepository,
    private val scheduleRepository: ScheduleRepository,
    private val attachmentRepository: AttachmentRepository,
    private val dispatchers: IDispatcherProvider
) : InsertTask {

    override suspend fun invoke(
        task: TaskEntity,
        noteList: List<NoteDomainModel>,
        scheduleList: List<ScheduleEntity>,
        attachmentList: List<AttachmentEntity>
    ) {
        return withContext(dispatchers.io) {
            val data = repository.insertTask(task)
            printDebug { "[InsertTaskUseCase]: Task uid :$data" }
            scheduleList.forEach {
                scheduleRepository.insertSchedule(it.copy(taskUid = data))
            }
            noteList.forEach {
                noteRepository.insertNote(it.copy(taskUid = data).asEntity())
            }
            attachmentList.forEach {
                attachmentRepository.insertAttachment(it.copy(attachmentTaskUid = data))
            }

            printDebug { "Inserted with note and task is $scheduleList and $noteList" }
        }
    }

}