package uz.behzod.eightytwenty.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithSchedule(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "task_uid",
        entityColumn = "task_uid"
    )
    val schedule: ScheduleEntity
)