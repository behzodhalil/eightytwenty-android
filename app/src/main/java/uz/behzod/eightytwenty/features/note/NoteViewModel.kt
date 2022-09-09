package uz.behzod.eightytwenty.features.note

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.domain.interactor.note.FetchNotes
import uz.behzod.eightytwenty.domain.interactor.note.FetchNotesByCategoryId
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val iFetchNotes: FetchNotes,
    private val iFetchNotesByCategoryId: FetchNotesByCategoryId
) : ViewModel() {

    private var _viewEffect = Channel<NoteViewEffect>(Channel.BUFFERED)
    val viewEffect: Flow<NoteViewEffect> = _viewEffect.receiveAsFlow()

    private var _uiState: MutableStateFlow<NoteUIState> = MutableStateFlow(NoteUIState.Loading)
    val uiState: Flow<NoteUIState> = _uiState.asStateFlow()

    private var _uiStateById: MutableStateFlow<NoteUIState> = MutableStateFlow(NoteUIState.Loading)
    val uiStateById: Flow<NoteUIState> = _uiStateById.asStateFlow()

    init {
        viewModelScope.launch {
            iFetchNotes.invoke().collect { result ->
                _uiState.value = NoteUIState.Loading
                if (result.isNullOrEmpty()) {
                    _uiState.value = NoteUIState.Empty
                } else {
                    _uiState.value = NoteUIState.Success(result)
                }
            }
        }
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            NoteEvent.CategoryEvent -> onObserveEffects(NoteViewEffect.CategoryViewEffect)
            NoteEvent.DetailEvent -> onObserveEffects(NoteViewEffect.DetailViewEffect)
            NoteEvent.NewNoteEvent -> onObserveEffects(NoteViewEffect.NewNoteClickViewEffect)
            NoteEvent.SearchEvent -> onObserveEffects(NoteViewEffect.DetailViewEffect)
        }
    }

    private fun onObserveEffects(effect: NoteViewEffect) {
        viewModelScope.launch {
            when (effect) {
                NoteViewEffect.CategoryViewEffect -> {
                    _viewEffect.trySend(NoteViewEffect.CategoryViewEffect)
                }
                NoteViewEffect.DetailViewEffect -> {
                    _viewEffect.trySend(NoteViewEffect.DetailViewEffect)
                }
                NoteViewEffect.NewNoteClickViewEffect -> {
                    _viewEffect.trySend(NoteViewEffect.NewNoteClickViewEffect)
                }
                NoteViewEffect.SearchViewEffect -> {
                    _viewEffect.trySend(NoteViewEffect.SearchViewEffect)
                }
            }
        }
    }

    fun fetchNotesByCategoryId(value: Long) {
        iFetchNotesByCategoryId.invoke(value)
            .onEach { result ->
                _uiStateById.value = NoteUIState.Loading
                if (result.isNullOrEmpty()) {
                    _uiStateById.value = NoteUIState.Empty
                } else {
                    _uiStateById.value = NoteUIState.Success(result)
                }
            }
            .launchIn(viewModelScope)
    }

}