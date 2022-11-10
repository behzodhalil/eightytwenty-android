package uz.behzod.eightytwenty

import uz.behzod.eightytwenty.data.local.entities.NoteCategoryEntity
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.data.local.entities.reminder.BillEntity
import uz.behzod.eightytwenty.data.local.entities.reminder.PillEntity
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

internal fun createTrashedNote(): NoteEntity {
    return NoteEntity(
        id = 1,
        title = "Google Manager",
        description = "Using google with map",
        timestamp = ZonedDateTime.now(),
        isTrashed = true,
        categoryId = 1
    )
}

internal fun createPill(): PillEntity {
    return PillEntity(
        duration = "100",
        frequency = "Daily",
        form = "Capsule",
        timestamp = "2022.11.12",
        dose = 50L,
        name = "Vitamin D",
        uid = 1
    )
}

internal fun createPillA(): PillEntity {
    return PillEntity(
        duration = "200",
        frequency = "Daily",
        form = "Capsule",
        timestamp = "2022.11.12",
        dose = 50L,
        name = "Vitamin D",
        uid = 1
    )
}

internal fun createBill(): BillEntity {
    return BillEntity(
        amount = 20,
        type = "House",
        timestamp = "2022.10.12",
        duration = 10
    )
}

