package uz.behzod.eightytwenty.domain.interactor.image

import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity

interface InsertNoteImage {
    suspend fun execute(value: NoteImageEntity): Long
}