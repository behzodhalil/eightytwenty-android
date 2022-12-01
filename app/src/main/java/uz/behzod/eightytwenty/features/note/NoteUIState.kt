package uz.behzod.eightytwenty.features.note

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.data.local.entities.NoteRelation
import uz.behzod.eightytwenty.utils.extension.Empty
import uz.behzod.eightytwenty.utils.extension.Zero


data class NoteViewState(
    val notes: List<NoteRelation> = emptyList(),
    val errorMessage: String = String.Empty,
    val uid: Long = Long.Zero,
    val groupUid: Long = Long.Zero,
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val isSuccess: Boolean = false
) : ViewState
