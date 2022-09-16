package uz.behzod.eightytwenty.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CatalogAndTasks(
    @Embedded val catalogEntity: TaskCatalogEntity,
    @Relation(
        parentColumn = "catalog_uid",
        entityColumn = "task_catalog_uid"
    )
    val tasks: List<TaskEntity> = emptyList()
)
