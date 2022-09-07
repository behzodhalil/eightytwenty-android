package uz.behzod.eightytwenty

import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.domain.NoteCategoryDomainModel
import uz.behzod.eightytwenty.domain.NoteDomainModel
import java.time.ZonedDateTime

fun createNoteEntity(): NoteEntity {
    return NoteEntity(
        id = 1,
        title = "Google Manager",
        description = "Using google with sync",
        timestamp = ZonedDateTime.now(),
        isTrashed = false,
        categoryId = 1
    )
}

fun createNoteDomainModel(): NoteDomainModel {
    return NoteDomainModel(
        id = 1,
        title = "Google Manager",
        description = "Using google with sync",
        timestamp = ZonedDateTime.now(),
        isTrashed = false,
        categoryId = 1
    )
}

fun createNoteCategoryEntity(): NoteCategoryEntity {
    return NoteCategoryEntity(
        id = 1,
        name = "All",
        count = 1
    )
}

fun createNoteCategoryDomainModel(): NoteCategoryDomainModel {
    return NoteCategoryDomainModel(
        id = 1,
        name = "All",
        count = 1
    )
}