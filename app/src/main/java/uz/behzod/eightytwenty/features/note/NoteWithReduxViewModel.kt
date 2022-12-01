package uz.behzod.eightytwenty.features.note

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.note.FetchNotesByCategoryId
import javax.inject.Inject

@HiltViewModel
class NoteWithReduxViewModel @Inject constructor(
    private val iFetchNotesByCategoryId: FetchNotesByCategoryId
) : ReduxViewModel<NoteViewState>(initialState = NoteViewState()) {


    fun fetchNotesByUid() {
        val groupUid = currentState.groupUid

        iFetchNotesByCategoryId.invoke(groupUid)
            .onStart { modifyState { state ->
                state.copy(
                    isLoading = true
                )
            } }
            .onEach {
                if (it.isNotEmpty()) {
                    modifyState { state ->
                        state.copy(
                            isLoading = false,
                            isSuccess = true,
                            notes = it
                        )
                    }
                } else {
                    modifyState { state -> state.copy(
                        isEmpty = true
                    ) }
                }
            }
            .launchIn(viewModelScope)

    }

    fun updateGroupUid(value: Long) {
        modifyState { state -> state.copy(groupUid = value) }
    }

}
