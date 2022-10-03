package uz.behzod.eightytwenty.features.new_note

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.note.InsertNote
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class NewNoteWithReduxViewModel @Inject constructor(
    private val iInsertNote: InsertNote
): ReduxViewModel<NewNoteViewState,NewNoteViewEffect>(initialState = NewNoteViewState()) {

    fun modifyTitle(title: String) {
        modifyState { state -> state.copy(title = title) }
    }

    fun modifyDesc(desc: String) {
        modifyState { state -> state.copy(description = desc) }
    }

    fun modifyTimestamp(timestamp: ZonedDateTime) {
        modifyState { state -> state.copy(timestamp = timestamp) }
    }

    fun modifyIsTrashed(isTrashed: Boolean) {
        modifyState { state -> state.copy(isTrashed = isTrashed) }
    }

    fun modifyGroupUid(uid: Long) {
        modifyState { state -> state.copy(categoryUid = uid) }
    }

    fun insertNote() {
        viewModelScope.launch {
            val title = state.value.title.trim()
            val desc = state.value.description.trim()
            val timestamp = state.value.timestamp
            val isTrashed = state.value.isTrashed

            modifyState { state -> state.copy(isLoading = true) }

            runCatching {
                iInsertNote.invoke(
                    NoteDomainModel(
                        title = title,
                        description = desc,
                        timestamp = timestamp,
                        isTrashed = isTrashed
                    )
                )
            }.onSuccess {
                modifyState { state ->
                    state.copy(
                        isSuccess = true,
                        isFailure = false,
                        isLoading = false,
                    )
                }
            }.onFailure {
                modifyState { state -> state.copy(isFailure = true) }
            }
        }
    }
}