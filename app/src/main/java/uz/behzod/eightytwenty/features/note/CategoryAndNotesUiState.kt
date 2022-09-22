package uz.behzod.eightytwenty.features.note

import uz.behzod.eightytwenty.data.local.entities.CategoryAndNotes

sealed class CategoryAndNotesUiState {
    data class Success(val data: List<CategoryAndNotes>): CategoryAndNotesUiState()
    data class Failure(val msg: String): CategoryAndNotesUiState()
    object Empty: CategoryAndNotesUiState()
    object Loading: CategoryAndNotesUiState()
}
