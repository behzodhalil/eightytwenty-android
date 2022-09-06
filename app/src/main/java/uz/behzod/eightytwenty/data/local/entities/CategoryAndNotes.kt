package uz.behzod.eightytwenty.data.local.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryAndNotes(
    @Embedded val noteCategoryEntity: NoteCategoryEntity,
    @Relation(
        parentColumn = Schema.ID,
        entityColumn = Schema.NOTE_CATEGORY_ID
    )
    val notes: List<NoteEntity>
): Parcelable {
    private object Schema {
        const val ID = "id"
        const val NOTE_CATEGORY_ID = "note_category_id"
    }
}
