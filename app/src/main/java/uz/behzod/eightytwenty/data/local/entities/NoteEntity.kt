package uz.behzod.eightytwenty.data.local.entities

import androidx.room.*
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

@Entity(
    tableName = "note_table",
    foreignKeys = [ForeignKey(
        entity = TaskEntity::class,
        parentColumns = ["task_uid"],
        childColumns = ["note_task_uid"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["note_task_uid"], unique = true)]
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Schema.ID)
    val id: Long = Long.Zero,

    @ColumnInfo(name = Schema.TITLE)
    val title: String = String.Empty,

    @ColumnInfo(name = Schema.DESCRIPTION)
    val description: String = String.Empty,

    @ColumnInfo(name = Schema.TIMESTAMP)
    val timestamp: ZonedDateTime = ZonedDateTime.now(),

    @ColumnInfo(name = Schema.IS_TRASHED)
    val isTrashed: Boolean = false,

    @ColumnInfo(name = Schema.CATEGORY_ID)
    val categoryId: Long = Long.Zero,

    @ColumnInfo(name = Schema.TASK_UID)
    val taskUid: Long? = null
) {
    private object Schema {
        const val TABLE_NAME = "note_table"
        const val ID = "note_id"
        const val TITLE = "note_title"
        const val DESCRIPTION = "note_description"
        const val TIMESTAMP = "note_timestamp"
        const val IS_TRASHED = "note_is_trashed"
        const val CATEGORY_ID = "note_category_id"
        const val TASK_UID = "note_task_uid"
    }
}

fun NoteEntity.asDomain(): NoteDomainModel {
    return NoteDomainModel(
        id = id,
        title = title,
        description = description,
        timestamp = timestamp,
        isTrashed = false,
        categoryId = categoryId,
        taskUid = taskUid
    )
}

fun NoteDomainModel.asEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        description = description,
        timestamp = timestamp,
        isTrashed = false,
        categoryId = categoryId,
        taskUid = taskUid
    )
}

fun List<NoteEntity>.asListOfDomain(): List<NoteDomainModel> = this.flatMap {
    listOf(it.asDomain())
}

fun List<NoteDomainModel>.asListOfEntity(): List<NoteEntity> = this.flatMap {
    listOf(it.asEntity())
}
