package uz.behzod.eightytwenty.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

@Entity(
    tableName = "task_entity_table_name"
)
data class TaskEntity(
    @ColumnInfo(name = TITLE)
    val title: String = String.Empty,

    @ColumnInfo(name = DUE_DATE)
    val dueDate: String = String.Empty,

    @ColumnInfo(name = FREQUENCY)
    val frequency: Frequency = Frequency.DAILY,

    @ColumnInfo(name = TIMESTAMP)
    val timestamp: String = String.Empty,

    @ColumnInfo(name = DEADLINE)
    val deadline: String = String.Empty,

    @ColumnInfo(name = IS_COMPLETE)
    val isComplete: Boolean = false,

    @ColumnInfo(name = IS_TRASHED)
    val isTrashed: Boolean = false,

    @ColumnInfo(name = CATALOG_NAME)
    val catalogName: String = String.Empty,

    @ColumnInfo(name = CATALOG_UID)
    val catalogUid: Long = Long.Zero,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = UID)
    val uid: Long = Long.Zero,
) {
    companion object {
        private const val TITLE = "task_title"
        private const val DUE_DATE = "task_due_date"
        private const val FREQUENCY = "task_frequency"
        private const val TIMESTAMP = "task_timestamp"
        private const val DEADLINE = "task_deadline"
        private const val IS_COMPLETE = "task_is_complete"
        private const val IS_TRASHED = "task_is_trashed"
        private const val CATALOG_NAME = "task_catalog_name"
        private const val CATALOG_UID = "task_catalog_uid"
        private const val UID ="uid"
    }
}
