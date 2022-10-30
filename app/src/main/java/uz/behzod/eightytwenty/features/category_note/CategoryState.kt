package uz.behzod.eightytwenty.features.category_note

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

data class CategoryState(
    val categories: List<NoteCategoryDomainModel> = emptyList(),
    val categoryName: String = String.Empty,
    val categoryUid: Long = Long.Zero,
    val onSuccess: Boolean = false,
    val onEmpty: Boolean = false,
    val onLoading: Boolean = false,
    val onSaved: Boolean = false,
    val onSaveFailed: Boolean = false,
    val onFillCategory: Boolean = false,
    val onDeleted: Boolean = false,
    val onDeleteFailed: Boolean = false
): ViewState {
    companion object {
        val empty  = CategoryState()
    }
}
