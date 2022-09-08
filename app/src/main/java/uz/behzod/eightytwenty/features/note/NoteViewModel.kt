package uz.behzod.eightytwenty.features.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor() : ViewModel() {

    private var _viewEffect = Channel<NoteViewEffect>(Channel.BUFFERED)
    val viewEffect: Flow<NoteViewEffect> = _viewEffect.receiveAsFlow()

    fun onEvent(event: NoteEvent) {
        when(event) {
            NoteEvent.CategoryEvent -> onObserveEffects(NoteViewEffect.CategoryViewEffect)
            NoteEvent.DetailEvent -> onObserveEffects(NoteViewEffect.DetailViewEffect)
            NoteEvent.NewNoteEvent -> onObserveEffects(NoteViewEffect.NewNoteClickViewEffect)
            NoteEvent.SearchEvent -> onObserveEffects(NoteViewEffect.DetailViewEffect)
        }
    }

    private fun onObserveEffects(effect: NoteViewEffect) {
        viewModelScope.launch {
            when(effect) {
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