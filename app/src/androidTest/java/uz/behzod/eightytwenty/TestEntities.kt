package uz.behzod.eightytwenty

import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import java.time.ZonedDateTime

internal fun createNoteCategory(): NoteCategoryEntity {
    return NoteCategoryEntity(
        id = 1,
        name = "All",
        count = 1
    )
}

internal fun createNote(): NoteEntity {
    return NoteEntity(
        id = 1,
        title = "Google Manager",
        description = "Using google with map",
        timestamp = ZonedDateTime.now(),
        isTrashed = false,
        categoryId = 1
    )
}

fun createTrashedNote(): NoteEntity {
    return NoteEntity(
        id = 1,
        title = "Google Manager",
        description = "Using google with map",
        timestamp = ZonedDateTime.now(),
        isTrashed = true,
        categoryId = 1
    )
}