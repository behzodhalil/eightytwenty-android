package uz.behzod.eightytwenty.data.local.entities

import android.content.Context
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import uz.behzod.eightytwenty.utils.extension.isToday
import uz.behzod.eightytwenty.utils.extension.isTomorrow
import uz.behzod.eightytwenty.utils.formatter.DateTimeFormatter
import java.time.ZonedDateTime

@Entity(
    tableName = "task_entity_table_name",
    foreignKeys = [ForeignKey(
        entity = TaskCatalogEntity::class,
        parentColumns = ["catalog_uid"],
        childColumns = ["task_catalog_uid"],
        onDelete = CASCADE
    )],
    indices = [
        Index(value = ["task_title"], unique = true),
        Index(value = ["task_timestamp"], unique = true)]
)
data class TaskEntity(
    @ColumnInfo(name = TITLE) val title: String = String.Empty,
    @ColumnInfo(name = DUE_DATE) val endDate: ZonedDateTime? = null,
    @ColumnInfo(name = FREQUENCY) val frequency: Frequency = Frequency.DAILY,
    @ColumnInfo(name = REMINDER) val reminderDateTime: ZonedDateTime? = null,
    @ColumnInfo(name = TIMESTAMP) val timestamp: ZonedDateTime? = null,
    @ColumnInfo(name = DEADLINE) val deadline: String = String.Empty,
    @ColumnInfo(name = IS_COMPLETE) val isComplete: Boolean = false,
    @ColumnInfo(name = IS_TRASHED) val isTrashed: Boolean = false,
    @ColumnInfo(name = CATALOG_UID) val catalogUid: Long = Long.Zero,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = UID) val uid: Long = Long.Zero,
) {
    companion object {
        private const val TITLE = "task_title"
        private const val DUE_DATE = "task_end_date"
        private const val FREQUENCY = "task_frequency"
        private const val TIMESTAMP = "task_timestamp"
        private const val DEADLINE = "task_deadline"
        private const val REMINDER = "task_reminder"
        private const val IS_COMPLETE = "task_is_complete"
        private const val IS_TRASHED = "task_is_trashed"
        private const val CATALOG_UID = "task_catalog_uid"
        private const val UID = "task_uid"
    }

    fun hasEndDate(): Boolean {
        return endDate != null
    }

    fun isEndDateToday(): Boolean {
        return endDate?.isToday() == true
    }

    fun isEndDateTomorrow(): Boolean {
        return endDate?.isTomorrow() == true
    }

    fun formatEndDate(context: Context): String? {
        return endDate?.format(DateTimeFormatter.asTime(context, true))
    }
}
