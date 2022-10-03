package uz.behzod.eightytwenty.features.note_detail

import uz.behzod.eightytwenty.core.state.ViewState
import uz.behzod.eightytwenty.data.local.entities.NoteEntity
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.extension.Empty
import java.sql.Timestamp

sealed class NoteDetailUIState {
    data class Success(val data: NoteDomainModel): NoteDetailUIState()
    data class Failure(val message: String): NoteDetailUIState()
    object Empty: NoteDetailUIState()
    object Loading: NoteDetailUIState()
}

data class NoteDetailViewState(
    val note: NoteDomainModel? = null,
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val isSuccess: Boolean = false,
): ViewState
