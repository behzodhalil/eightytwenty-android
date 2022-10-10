package uz.behzod.eightytwenty.domain.interactor.image

import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity

interface UpdateNoteImage {
    suspend fun execute(value: NoteImageEntity)
}