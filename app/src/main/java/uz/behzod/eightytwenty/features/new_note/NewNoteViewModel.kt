package uz.behzod.eightytwenty.features.new_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.behzod.eightytwenty.domain.interactor.note.InsertNote
import uz.behzod.eightytwenty.domain.model.NoteDomainModel
import uz.behzod.eightytwenty.utils.providers.IDispatcherProvider
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val insertNoteInteractor: InsertNote,
    private val dispatcherProvider: IDispatcherProvider
) : ViewModel() {

    private var _viewEffect: Channel<NewNoteViewEffect> = Channel(Channel.BUFFERED)
    val viewEffect: Flow<NewNoteViewEffect> = _viewEffect.receiveAsFlow()

    fun onListenerEvents(event: NewNoteEvent) {
        when(event) {
            NewNoteEvent.RedoEvent -> onObserverEffects(NewNoteViewEffect.RedoViewEffect)
            NewNoteEvent.SavedEvent -> onObserverEffects(NewNoteViewEffect.SavedViewEffect)
            NewNoteEvent.UndoEvent -> onObserverEffects(NewNoteViewEffect.UndoViewEffect)
        }
    }

    private fun onObserverEffects(effect: NewNoteViewEffect) {
        viewModelScope.launch {
            when(effect) {
                NewNoteViewEffect.RedoViewEffect -> _viewEffect.trySend(NewNoteViewEffect.RedoViewEffect)
                NewNoteViewEffect.SavedViewEffect -> _viewEffect.trySend(NewNoteViewEffect.SavedViewEffect)
                NewNoteViewEffect.UndoViewEffect -> _viewEffect.trySend(NewNoteViewEffect.UndoViewEffect)
            }
        }
    }

    fun insertNote(note: NoteDomainModel) {
        viewModelScope.launch {
            insertNoteInteractor.invoke(
                note
            )
        }
    }

}