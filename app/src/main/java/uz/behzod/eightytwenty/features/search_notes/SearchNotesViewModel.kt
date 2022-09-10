package uz.behzod.eightytwenty.features.search_notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.behzod.eightytwenty.domain.interactor.note.SearchNotes
import javax.inject.Inject

@HiltViewModel
class SearchNotesViewModel @Inject constructor(
    private val searchNotesInteractor: SearchNotes
) : ViewModel() {

    private var _uiState: MutableStateFlow<SearchNotesUIState> =
        MutableStateFlow(SearchNotesUIState.Loading)
    val uiState = _uiState.asStateFlow()

    fun searchNotes(query: String) {
        searchNotesInteractor.invoke(query).onEach {
            if (it.isEmpty()) {
                _uiState.value = SearchNotesUIState.Empty
            } else if (it.isNotEmpty()) {
                _uiState.value = SearchNotesUIState.Success(it)
            }
        }.launchIn(viewModelScope)
    }
}