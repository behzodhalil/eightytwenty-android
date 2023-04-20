package uz.behzod.eightytwenty.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import uz.behzod.eightytwenty.utils.extension.Empty
import java.time.ZonedDateTime

@Entity(tableName = "note_fts_table")
@Fts4(contentEntity = NoteEntity::class)
data class NoteFTS(
    @ColumnInfo(name = "note_title")
    val title: String = String.Empty,
    @ColumnInfo(name = "note_description")
    val description: String = String.Empty,
    @ColumnInfo(name = "note_timestamp")
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
    @ColumnInfo(name = "note_is_trashed")
    val isTrashed: Boolean = false,
)
