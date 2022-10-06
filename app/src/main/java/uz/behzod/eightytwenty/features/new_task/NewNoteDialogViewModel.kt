package uz.behzod.eightytwenty.features.new_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.domain.interactor.note.InsertNote
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import javax.inject.Inject

@HiltViewModel
class NewNoteDialogViewModel @Inject constructor(
    private val iInsertNote: InsertNote
) : ViewModel() {

    private var _uiState: MutableStateFlow<NewNoteUiState> =
        MutableStateFlow(NewNoteUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun insertNote(note: NoteDomainModel) {
        viewModelScope.launch {
            _uiState.value = loading()
            try {
                iInsertNote.invoke(note, emptyList())
                _uiState.value = success()
            } catch (e: Exception) {
                _uiState.value = failure()
            }
        }
    }
}