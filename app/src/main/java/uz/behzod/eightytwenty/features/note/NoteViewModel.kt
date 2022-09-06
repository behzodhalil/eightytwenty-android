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
            NoteEvent.CategoryEvent -> observeEffects(NoteViewEffect.CategoryViewEffect)
            NoteEvent.DetailEvent -> observeEffects(NoteViewEffect.DetailViewEffect)
            NoteEvent.NewNoteEvent -> observeEffects(NoteViewEffect.NewNoteViewEffect)
            NoteEvent.SearchEvent -> observeEffects(NoteViewEffect.DetailViewEffect)
        }
    }

    private fun observeEffects(effect: NoteViewEffect) {
        viewModelScope.launch {
            when(effect) {
                NoteViewEffect.CategoryViewEffect -> {
                    _viewEffect.trySend(NoteViewEffect.CategoryViewEffect)
                }
                NoteViewEffect.DetailViewEffect -> {
                    _viewEffect.trySend(NoteViewEffect.DetailViewEffect)
                }
                NoteViewEffect.NewNoteViewEffect -> {
                    _viewEffect.trySend(NoteViewEffect.NewNoteViewEffect)
                }
                NoteViewEffect.SearchViewEffect -> {
                    _viewEffect.trySend(NoteViewEffect.SearchViewEffect)
                }
            }
        }
    }
}