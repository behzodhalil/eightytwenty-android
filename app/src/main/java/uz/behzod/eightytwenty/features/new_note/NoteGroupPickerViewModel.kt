package uz.behzod.eightytwenty.features.new_note

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.note_category.FetchAllCategories
import javax.inject.Inject

@HiltViewModel
class NoteGroupPickerViewModel @Inject constructor(
    defaultFetchAllCategories: FetchAllCategories
) : ReduxViewModel<NoteGroupPickerState>(initialState = NoteGroupPickerState.Empty) {

    init {
        defaultFetchAllCategories.invoke()
            .onStart { modifyState { state ->
                state.copy(onLoading = true)
            } }
            .onEach { result ->
                if (result.isNotEmpty()) {
                    modifyState { state ->
                        state.copy(
                            onLoading = false,
                            onEmpty = false,
                            onSuccess = true,
                            groups = result
                        ) }
                } else {
                    modifyState { state ->
                        state.copy(
                            onLoading = false,
                            onSuccess = false,
                            onEmpty = true
                        )
                    }
                }


            }.launchIn(viewModelScope)
    }

    fun updateName(value: String) {
        modifyState { state -> state.copy(
            name = value
        ) }
    }

    fun updateUid(value: Long) {
        modifyState { state -> state.copy(
            uid = value
        ) }
    }
}