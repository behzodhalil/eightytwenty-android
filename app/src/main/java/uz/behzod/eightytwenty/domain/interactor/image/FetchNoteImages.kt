package uz.behzod.eightytwenty.domain.interactor.image

import kotlinx.coroutines.flow.Flow
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity

interface FetchNoteImages {
    fun execute(): Flow<List<NoteImageEntity>>
}