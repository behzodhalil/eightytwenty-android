package uz.behzod.eightytwenty.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TaskAndSchedule(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "task_uid",
        entityColumn = "schedule_task_uid"
    )
    val scheduleEntity: ScheduleEntity
)
