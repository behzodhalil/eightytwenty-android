package uz.behzod.eightytwenty.data.local.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import uz.behzod.eightytwenty.utils.ext.Empty
import uz.behzod.eightytwenty.utils.ext.Zero
import java.time.ZonedDateTime

@Entity(
    tableName = "note_table"
)
@Parcelize
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
    val categoryId: Long = Long.Zero
): Parcelable {
    private object Schema {
        const val TABLE_NAME = "note_table"
        const val ID = "note_id"
        const val TITLE = "note_title"
        const val DESCRIPTION = "note_description"
        const val TIMESTAMP = "note_timestamp"
        const val IS_TRASHED = "note_is_trashed"
        const val CATEGORY_ID = "note_category_id"
    }
}
