package uz.behzod.eightytwenty.data.local.entities

import androidx.room.*
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.time.ZonedDateTime

@Entity(
    tableName = "attachment_table",
    foreignKeys = [ForeignKey(
        entity = TaskEntity::class,
        parentColumns = ["task_uid"],
        childColumns = ["attachment_task_uid"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["attachment_task_uid"], unique = true)]
)
data class AttachmentEntity(
    @ColumnInfo(name = ATTACHMENT_NAME) val attachmentName: String = String.Empty,
    @ColumnInfo(name = ATTACHMENT_TARGET) val attachmentTarget: String = String.Empty,
    @ColumnInfo(name = ATTACHMENT_TYPE) val attachmentType: Int = Int.Zero,
    @ColumnInfo(name = ATTACHMENT_TIMESTAMP)
    val attachmentTimestamp: ZonedDateTime = ZonedDateTime.now(),
    @ColumnInfo(name = ATTACHMENT_TASK_UID) val attachmentTaskUid: Long? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "") val attachmentUid: Long = Long.Zero
) {
    companion object {
        const val ATTACHMENT_TABLE = "attachment_table"
        private const val ATTACHMENT_NAME = "attachment_name"
        private const val ATTACHMENT_TARGET = "attachment_target"
        private const val ATTACHMENT_TYPE = "attachment_type"
        private const val ATTACHMENT_TIMESTAMP = "attachment_timestamp"
        private const val ATTACHMENT_TASK_UID = "attachment_task_uid"
        private const val ATTACHMENT_UID = "attachment_uid"
    }
}
