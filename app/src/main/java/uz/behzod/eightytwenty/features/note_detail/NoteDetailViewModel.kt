package uz.behzod.eightytwenty.features.note_detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.data.local.dao.NoteDao
import uz.behzod.eightytwenty.data.local.entities.isNotEmpty
import uz.behzod.eightytwenty.domain.interactor.note.DefaultFetchNoteRelationByUid
import uz.behzod.eightytwenty.domain.interactor.note.FetchNoteById
import uz.behzod.eightytwenty.domain.interactor.note.FetchNoteRelationByUid
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val iUpdateNote: NoteDao,
    private val iFetchNoteById: FetchNoteById,
    private val defaultFetchNoteRelationByUid: FetchNoteRelationByUid
): ReduxViewModel<NoteDetailState,NoteDetailViewEffect>(NoteDetailState()) {



    fun fetchNoteRelationByUid(uid: Long) {
        defaultFetchNoteRelationByUid.execute(noteUid = uid).onEach {
            modifyState { state -> state.copy(isLoading = true) }
            if (it.isNotEmpty()) {
                modifyState { state -> state.copy(
                    isLoading = false,
                    isSuccess = true,
                    isFailure = false,
                    note = it
                ) }
            } else {
                modifyState { state -> state.copy(
                    isLoading = false,
                    isSuccess = false,
                    isFailure = true
                ) }
            }
        }.launchIn(viewModelScope)
    }
}