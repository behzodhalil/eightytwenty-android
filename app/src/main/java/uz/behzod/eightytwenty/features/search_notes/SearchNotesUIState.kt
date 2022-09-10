package uz.behzod.eightytwenty.features.search_notes

import uz.behzod.eightytwenty.domain.model.NoteDomainModel

sealed class SearchNotesUIState {
    data class Success(val data: List<NoteDomainModel>): SearchNotesUIState()
    data class Failure(val message: String): SearchNotesUIState()
    object Empty: SearchNotesUIState()
    object Loading: SearchNotesUIState()
}