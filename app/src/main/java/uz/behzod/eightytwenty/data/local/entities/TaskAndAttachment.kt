package uz.behzod.eightytwenty.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TaskAndAttachment(
    @Embedded val task: TaskEntity,
    @Relation(parentColumn = "task_uid", entityColumn = "attachment_task_uid")
    val attachment: AttachmentEntity,
    @Relation(parentColumn = "task_uid", entityColumn = "note_task_uid")
    val note: NoteEntity,
    @Relation(parentColumn = "task_uid", entityColumn = "schedule_task_uid")
    val schedule: ScheduleEntity
)
