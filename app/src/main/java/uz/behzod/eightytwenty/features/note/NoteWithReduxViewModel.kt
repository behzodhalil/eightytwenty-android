package uz.behzod.eightytwenty.features.note

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.note.FetchNotes
import uz.behzod.eightytwenty.domain.interactor.note.FetchNotesByCategoryId
import uz.behzod.eightytwenty.domain.interactor.note_category.FetchAllCategoriesAndNotes
import uz.behzod.eightytwenty.utils.extension.debugger
import javax.inject.Inject

@HiltViewModel
class NoteWithReduxViewModel @Inject constructor(
    private val iFetchNotes: FetchNotes,
    private val iFetchNotesByCategoryId: FetchNotesByCategoryId,
    private val iFetchCategoryAndNotes: FetchAllCategoriesAndNotes
) : ReduxViewModel<NoteViewState,NoteViewEffect>(initialState = NoteViewState()) {

    init {
        iFetchNotes.invoke()
            .onEach { result ->
                if (result.isNotEmpty()) {
                    modifyState { state ->
                        state.copy(
                            notes = result,
                            isLoading = false,
                            isEmpty = false
                        )
                    }
                    debugger { "Result is $result" }
                } else {
                    modifyState { state ->
                        state.copy(
                            notes = emptyList(),
                            isLoading = false,
                            isEmpty = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
    }
}