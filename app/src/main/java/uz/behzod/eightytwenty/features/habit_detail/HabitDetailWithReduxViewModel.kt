package uz.behzod.eightytwenty.features.habit_detail

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.habit.FetchHabitByUid
import uz.behzod.eightytwenty.domain.model.HabitDomainModel
import javax.inject.Inject

@HiltViewModel
class HabitDetailWithReduxViewModel @Inject constructor(
    private val iFetchHabitByUid: FetchHabitByUid
) : ReduxViewModel<HabitDetailState>(
    initialState = HabitDetailState()
) {

    fun modifyUid(value: Long) {
        modifyState { state -> state.copy(uid = value) }
    }

    fun fetchHabitByUid() {
        val habitUid = currentState.uid

        iFetchHabitByUid.invoke(uid = habitUid)
            .onStart { modifyState { state -> state.copy(isLoading = true) } }
            .onEach { habit: HabitDomainModel? ->
                if (habit != null) {
                    modifyState { state ->
                        state.copy(
                            habit = habit,
                            isSuccess = true,
                            isLoading = false,
                            isEmpty = false
                        )
                    }
                } else {
                    modifyState { state ->
                        state.copy(
                            isSuccess = false,
                            isLoading = false,
                            isEmpty = true
                        )
                    }
                }
            }
    }
}