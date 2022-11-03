package uz.behzod.eightytwenty.features.note

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.data.local.entities.NoteRelation
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero

sealed class NoteUIState {
    data class Success(val data: List<NoteDomainModel>) : NoteUIState()
    data class Failure(val message: String) : NoteUIState()
    object Loading : NoteUIState()
    object Empty : NoteUIState()
}

data class NoteViewState(
    val notes: List<NoteRelation> = emptyList(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val errorMessage: String = String.Empty,
    val uid: Long = Long.Zero,
    val groupUid: Long = Long.Zero,
    val onSuccess: Boolean = false
) : ViewState