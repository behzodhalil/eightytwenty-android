package uz.behzod.eightytwenty.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryAndNotes(
    @Embedded val noteCategoryEntity: NoteCategoryEntity,
    @Relation(
        parentColumn = Schema.ID,
        entityColumn = Schema.NOTE_CATEGORY_ID
    )
    val notes: List<NoteEntity>
) {
    private object Schema {
        const val ID = "id"
        const val NOTE_CATEGORY_ID = "note_category_id"
    }
}
