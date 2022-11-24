package uz.behzod.eightytwenty.features.reminder

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.pill.FetchPills
import javax.inject.Inject

@HiltViewModel
class PillViewModel @Inject constructor(
    fetchPills: FetchPills,
) : ReduxViewModel<PillState>(initialState = PillState()) {

    init {
        fetchPills.execute()
            .onStart {
                modifyState { state ->
                    state.copy(isLoading = true)
                }
            }
            .onEach {
                if (it.isNotEmpty()) {
                    modifyState { state ->
                        state.copy(pills = it, isSuccess = true
                        )
                    }
                } else {
                    modifyState { state ->
                        state.copy(isEmpty = true, isLoading = false)
                    }
                }
            }.launchIn(viewModelScope)

    }
}
