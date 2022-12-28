package uz.behzod.eightytwenty.data.local.entities

import androidx.room.Entity
import androidx.room.Fts4
import uz.behzod.eightytwenty.utils.extension.Empty
import java.time.ZonedDateTime

@Entity(tableName = "note_entity_fts_table_name")
@Fts4(contentEntity = NoteEntity::class)
data class NoteEntityFTS(
    val title: String = String.Empty,
    val description: String = String.Empty,
    val timestamp: ZonedDateTime = ZonedDateTime.now()
)
