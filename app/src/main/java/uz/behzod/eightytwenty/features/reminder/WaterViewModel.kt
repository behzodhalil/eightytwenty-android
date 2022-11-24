package uz.behzod.eightytwenty.features.reminder

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.water.FetchWaters
import javax.inject.Inject

@HiltViewModel
class WaterViewModel @Inject constructor(
    fetchWaters: FetchWaters
): ReduxViewModel<WaterState>(initialState = WaterState()) {

    init {
        fetchWaters.execute().onStart {
            modifyState { state ->
                state.copy(isLoading = true)
            }
        }.onEach {
            if (it.isNotEmpty()) {
                modifyState { state ->
                    state.copy(
                        waters = it,
                        isLoading = false,
                        isSuccess = true
                    )
                }
            } else {
                modifyState { state ->
                    state.copy(
                        isLoading = false,
                        isEmpty = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
