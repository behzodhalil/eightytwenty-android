package uz.behzod.eightytwenty.features.note

import uz.behzod.eightytwenty.data.local.entities.CategoryAndNotes
import uz.behzod.eightytwenty.domain.model.CategoryAndNotesDomainModel

sealed class CategoryAndNotesUiState {
    data class Success(val data: List<CategoryAndNotesDomainModel>): CategoryAndNotesUiState()
    data class Failure(val msg: String): CategoryAndNotesUiState()
    object Empty: CategoryAndNotesUiState()
    object Loading: CategoryAndNotesUiState()
}
