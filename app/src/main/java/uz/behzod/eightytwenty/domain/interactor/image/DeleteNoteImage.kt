package uz.behzod.eightytwenty.domain.interactor.image

import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity

interface DeleteNoteImage {
    suspend fun execute(value: NoteImageEntity)
}