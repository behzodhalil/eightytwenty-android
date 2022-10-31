package uz.behzod.eightytwenty.features.new_note

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.domain.model.NoteCategoryDomainModel
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

data class NoteGroupPickerState(
    val groups: List<NoteCategoryDomainModel> = emptyList(),
    val name: String = String.Empty,
    val uid: Long = Long.Zero,
    val onSuccess: Boolean = false,
    val onFailure: Boolean = false,
    val onLoading: Boolean = false,
    val onEmpty: Boolean = false
): ViewState {
    companion object {
        val Empty = NoteGroupPickerState()
    }
}
