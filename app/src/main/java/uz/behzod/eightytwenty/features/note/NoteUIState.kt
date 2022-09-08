package uz.behzod.eightytwenty.features.note

import uz.behzod.eightytwenty.domain.model.NoteDomainModel

sealed class NoteUIState {
    data class Success(val data: List<NoteDomainModel>): NoteUIState()
    data class Failure(val message: String): NoteUIState()
    object Loading: NoteUIState()
    object Empty: NoteUIState()
}
