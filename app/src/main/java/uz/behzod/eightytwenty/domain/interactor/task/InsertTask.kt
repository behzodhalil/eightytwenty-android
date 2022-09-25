package uz.behzod.eightytwenty.domain.interactor.task

import uz.behzod.eightytwenty.data.local.entities.AttachmentEntity
import uz.behzod.eightytwenty.data.local.entities.ScheduleEntity
import uz.behzod.eightytwenty.data.local.entities.TaskEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

interface InsertTask {
    suspend operator fun invoke(
        task: TaskEntity,
        noteList: List<NoteDomainModel>,
        scheduleList: List<ScheduleEntity>,
        attachmentList: List<AttachmentEntity>
    )
}