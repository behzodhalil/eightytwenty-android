package uz.behzod.eightytwenty.features.note

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.extension.Zero

sealed class NoteUIState {
    data class Success(val data: List<NoteDomainModel>) : NoteUIState()
    data class Failure(val message: String) : NoteUIState()
    object Loading : NoteUIState()
    object Empty : NoteUIState()
}

data class NoteViewState(
    val notes: List<NoteDomainModel> = emptyList(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val errorMessage: String = "",
    val uid: Long = Long.Zero
) : ViewState