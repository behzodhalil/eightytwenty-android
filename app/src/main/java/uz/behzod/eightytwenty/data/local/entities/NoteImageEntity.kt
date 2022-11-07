package uz.behzod.eightytwenty.data.local.entities

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero
import java.util.UUID

@Entity(
    tableName = "note_image_table",
    foreignKeys = [ForeignKey(
        entity = NoteEntity::class,
        parentColumns = ["note_id"],
        childColumns = ["note_uid"],
        onDelete = CASCADE
    )]
)
data class NoteImageEntity(
    @ColumnInfo(name = URI) val uri: Uri? = null,
    @ColumnInfo(name = MIME_TYPE) val mimeType: String = String.Empty,
    @ColumnInfo(name = DESCRIPTION) val description: String = String.Empty,
    @ColumnInfo(name = NOTE_UID) var noteUid: Long = Long.Zero,
    @PrimaryKey
    @ColumnInfo(name = IMAGE_UID)
    var imageUid: String = UUID.randomUUID().toString(),
) {
    companion object {
        private const val URI = "uri"
        private const val MIME_TYPE = "mime_type"
        private const val DESCRIPTION = "description"
        private const val NOTE_UID = "note_uid"
        private const val IMAGE_UID = "image_uid"

        fun generateUid(): String {
            return UUID.randomUUID().toString()
        }
    }

}