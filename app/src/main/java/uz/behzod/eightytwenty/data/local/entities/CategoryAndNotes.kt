package uz.behzod.eightytwenty.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import uz.behzod.eightytwenty.domain.repository.CategoryAndNotesDomainModel

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

fun CategoryAndNotes.asDomain(): CategoryAndNotesDomainModel {
    return CategoryAndNotesDomainModel(
        category = noteCategoryEntity.asDomain(),
        listNotes = notes.map { it.asDomain() }
    )
}

fun CategoryAndNotesDomainModel.asEntity(): CategoryAndNotes {
    return CategoryAndNotes(
        noteCategoryEntity = category.asEntity(),
        notes = listNotes.map { it.asEntity() }
    )
}

fun List<CategoryAndNotes>.asListOfDomain(): List<CategoryAndNotesDomainModel> = this.flatMap {
    listOf(it.asDomain())
}

fun List<CategoryAndNotesDomainModel>.asListOfEntity(): List<CategoryAndNotes> =
    this.flatMap {
        listOf(it.asEntity())
}
