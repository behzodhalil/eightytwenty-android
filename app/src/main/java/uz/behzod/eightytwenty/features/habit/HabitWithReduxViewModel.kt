package uz.behzod.eightytwenty.features.habit

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.habit.FetchHabitsByDate
import javax.inject.Inject

@HiltViewModel
class HabitWithReduxViewModel @Inject constructor(
    private val iFetchHabitsByDate: FetchHabitsByDate
) : ReduxViewModel<HabitState, HabitAction>(
    initialState = HabitState()
) {
    fun modifyTimestamp(value: String) {
        modifyState { state -> state.copy(timestamp = value) }
    }

    fun fetchHabitsByTimestamp() {
        val timestamp = currentState.timestamp

        iFetchHabitsByDate.invoke(timestamp)
            .onStart { modifyState { state -> state.copy(isLoading = true) } }
            .onEach {
                if (it.isNotEmpty()) {
                    modifyState { state ->
                        state.copy(
                            habits = it,
                            isSuccess = true,
                            isLoading = false,
                            isEmpty = false
                        )
                    }
                }
                if (it.isEmpty()) {
                    modifyState { state ->
                        state.copy(
                            isSuccess = false,
                            isLoading = false,
                            isEmpty = true
                        )
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}