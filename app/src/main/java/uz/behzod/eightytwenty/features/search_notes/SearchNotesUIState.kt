package uz.behzod.eightytwenty.features.search_notes

import uz.behzod.eightytwenty.data.local.entities.NoteEntity

sealed class SearchNotesUIState {
    data class Success(val data: List<NoteEntity>): SearchNotesUIState()
    data class Failure(val message: String): SearchNotesUIState()
    object Empty: SearchNotesUIState()
    object Loading: SearchNotesUIState()
}
