package uz.behzod.eightytwenty

import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity

fun createNoteCategory(): NoteCategoryEntity {
    return NoteCategoryEntity(
        id = 1,
        name = "All",
        count = 1
    )
}