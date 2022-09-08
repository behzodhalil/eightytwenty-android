package uz.behzod.eightytwenty.features.note

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.domain.interactor.note.FetchNotes
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val iFetchNotes: FetchNotes
) : ViewModel() {

    private var _viewEffect = Channel<NoteViewEffect>(Channel.BUFFERED)
    val viewEffect: Flow<NoteViewEffect> = _viewEffect.receiveAsFlow()

    private var _uiState: MutableStateFlow<NoteUIState> = MutableStateFlow(NoteUIState.Loading)
    val uiState: Flow<NoteUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            Log.d("NoteViewModel","fetchNotes is called in viewModelScope")
            iFetchNotes.invoke().collect { result ->
                Log.d("NoteViewModel","fetchNotes is collected")
                Log.d("NoteViewModel","${result[0]}")
                _uiState.value = NoteUIState.Loading
                if (result.isNullOrEmpty()) {
                    Log.d("NoteViewModel","NoteUIState value is empty")
                    _uiState.value = NoteUIState.Empty
                } else {
                    _uiState.value = NoteUIState.Success(result)
                    Log.d("NoteViewModel","NoteUIState value is success")
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


}