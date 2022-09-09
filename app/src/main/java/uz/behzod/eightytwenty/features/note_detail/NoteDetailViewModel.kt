package uz.behzod.eightytwenty.features.note_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.domain.interactor.note.FetchNoteById
import uz.behzod.eightytwenty.domain.interactor.note.UpdateNote
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val iUpdateNote: UpdateNote,
    private val iFetchNoteById: FetchNoteById
) : ViewModel() {

    private var _uiState: MutableStateFlow<NoteDetailUIState> =
        MutableStateFlow(NoteDetailUIState.Loading)
    val uiState: StateFlow<NoteDetailUIState> = _uiState.asStateFlow()

    fun fetchNoteByUid(uid: Long) {
        viewModelScope.launch {
            _uiState.value = NoteDetailUIState.Loading
            iFetchNoteById.invoke(uid).collect { data: NoteDomainModel? ->
                if (data != null) {
                    _uiState.value = NoteDetailUIState.Success(data)
                } else {
                    _uiState.value = NoteDetailUIState.Empty
                }

            }
        }
    }

    fun updateNote(data: NoteDomainModel) {
        viewModelScope.launch {
            iUpdateNote.invoke(data)
        }
    }

}