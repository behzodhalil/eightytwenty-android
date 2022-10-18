package uz.behzod.eightytwenty.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class NoteRelation(
    @Embedded val note: NoteEntity,
    @Relation(
        parentColumn = "note_id",
        entityColumn = "note_uid"
    )
    val images: List<NoteImageEntity> = emptyList()
)

fun NoteRelation.isEmpty(): Boolean {
    return note.title.isEmpty()
}

fun NoteRelation.isNotEmpty(): Boolean {
    return note.title.isNotEmpty()
}
