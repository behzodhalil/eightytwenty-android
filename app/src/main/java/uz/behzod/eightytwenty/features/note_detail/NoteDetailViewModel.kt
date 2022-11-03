package uz.behzod.eightytwenty.features.note_detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.data.local.entities.NoteImageEntity
import uz.behzod.eightytwenty.data.local.entities.isNotEmpty
import uz.behzod.eightytwenty.domain.interactor.note.DeleteNote
import uz.behzod.eightytwenty.domain.interactor.note.FetchNoteRelationByUid
import uz.behzod.eightytwenty.domain.interactor.note.MoveToGroupNote
import uz.behzod.eightytwenty.domain.interactor.note.UpdateNote
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.extension.printDebug
import java.time.ZonedDateTime
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val defaultUpdateNote: UpdateNote,
    private val defaultDeleteNote: DeleteNote,
    private val defaultFetchNoteRelationByUid: FetchNoteRelationByUid,
    private val defaultMoveToGroupNote: MoveToGroupNote
) : ReduxViewModel<NoteDetailState>(NoteDetailState()) {

    fun updateTitle(value: String) {
        modifyState { state -> state.copy(title = value) }
    }

    fun updateDesc(value: String) {
        modifyState { state -> state.copy(description = value) }
    }

    fun updateGroupUid(value: Long) {
        modifyState { state -> state.copy(categoryUid = value) }
    }

    fun updateNoteUid(value: Long) {
        modifyState { state -> state.copy(uid = value) }
    }

    fun updateImages(values: List<NoteImageEntity>) {
        modifyState { state -> state.copy(images = values) }
    }

    fun updateTimestamp(value: ZonedDateTime) {
        modifyState { state -> state.copy(timestamp = value) }
    }

    fun updateNotes(value: List<NoteDomainModel>) {
        modifyState { state -> state.copy(notes = value) }
    }
    fun updateNote() {
        viewModelScope.launch {
            val title = currentState.title
            val desc = currentState.description
            val groupUid = currentState.categoryUid
            val timestamp = currentState.timestamp
            val uid = currentState.uid


            val note = NoteDomainModel(
                id = uid, title = title,
                description = desc, categoryId = groupUid, timestamp = timestamp
            )

            runCatching {
                defaultUpdateNote.invoke(note)
            }.onSuccess {
                modifyState { state -> state.copy(isUpdated = true, isUpdateFailed = false) }
            }.onFailure {
                modifyState { state -> state.copy(isUpdateFailed = true, isUpdated = false) }
            }
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            val title = currentState.title
            val desc = currentState.description
            val groupUid = currentState.uid
            val uid = currentState.uid
            val images = currentState.images
            val timestamp = currentState.timestamp

            val note = NoteDomainModel(
                id = uid, title = title,
                description = desc, categoryId = groupUid, timestamp = timestamp
            )

            runCatching {
                defaultDeleteNote.invoke(data = note,images = images)
            }.onSuccess {
                modifyState { state ->
                    state.copy(
                        isDeleted = true, isDeleteFailed = false
                    )
                }
            }.onFailure {
                modifyState { state ->
                    state.copy(
                        isDeleteFailed = true, isDeleted = false
                    )
                }
            }
        }
    }

    fun fetchNoteRelationByUid(uid: Long) {
        defaultFetchNoteRelationByUid.execute(noteUid = uid).onEach {
            modifyState { state -> state.copy(isLoading = true) }
            if (it.isNotEmpty()) {
                modifyState { state ->
                    state.copy(
                        isLoading = false,
                        isSuccess = true,
                        isFailure = false,
                        note = it
                    )
                }
            } else {
                modifyState { state ->
                    state.copy(
                        isLoading = false,
                        isSuccess = false,
                        isFailure = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun moveToGroup() {
        viewModelScope.launch {
            val notes = currentState.notes
            val groupUid = currentState.categoryUid
            printDebug { "Uid: $groupUid" }
            runCatching {
                if (groupUid!=0L) {
                    defaultMoveToGroupNote.execute(
                        notes,groupUid
                    )
                }
            }.onSuccess {
                modifyState { state ->
                    state.copy(isMoveFailed = false, isMoved = true)
                }
            }.onFailure { modifyState { state ->
                state.copy(isMoveFailed = true)
            } }

        }

    }
}