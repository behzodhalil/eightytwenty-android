package uz.behzod.eightytwenty.features.note_detail

import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel

sealed class NoteDetailUIState {
    data class Success(val data: NoteDomainModel): NoteDetailUIState()
    data class Failure(val message: String): NoteDetailUIState()
    object Empty: NoteDetailUIState()
    object Loading: NoteDetailUIState()
}
