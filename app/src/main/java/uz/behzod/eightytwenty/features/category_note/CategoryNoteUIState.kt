package uz.behzod.eightytwenty.features.category_note

import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel

sealed class CategoryNoteUIState {
    data class Success(val data: List<NoteCategoryDomainModel>): CategoryNoteUIState()
    data class Failure(val message: String): CategoryNoteUIState()
    object Loading: CategoryNoteUIState()
    object Empty: CategoryNoteUIState()
}
