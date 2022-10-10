package uz.behzod.eightytwenty.domain.interactor.image

import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity

interface InsertNoteImages {
    suspend fun execute(values: List<NoteImageEntity>)
}