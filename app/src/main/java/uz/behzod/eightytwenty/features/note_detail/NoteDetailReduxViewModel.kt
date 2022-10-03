package uz.behzod.eightytwenty.features.note_detail

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.data.local.dao.NoteDao
import uz.behzod.eightytwenty.domain.interactor.note.FetchNoteById
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import javax.inject.Inject

@HiltViewModel
class NoteDetailReduxViewModel @Inject constructor(
    private val iUpdateNote: NoteDao,
    private val iFetchNoteById: FetchNoteById
): ReduxViewModel<NoteDetailViewState,NoteDetailViewEffect>(NoteDetailViewState()) {

    fun fetchNoteByUid(uid: Long) {
        iFetchNoteById.invoke(uid).onEach { note: NoteDomainModel? ->
            modifyState { state -> state.copy(isLoading = true) }
            if (note!=null) {
                modifyState { state -> state.copy(isLoading = false, isSuccess = true, note = note) }
            } else {
                modifyState { state -> state.copy(isLoading = false, isFailure = true) }
            }
        }.launchIn(viewModelScope)
    }
}