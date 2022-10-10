package uz.behzod.eightytwenty.features.habit_recommend

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import uz.behzod.eightytwenty.core.ReduxViewModel
import uz.behzod.eightytwenty.domain.interactor.habit_recommend.FetchHabitRecommendsByCategory
import uz.behzod.eightytwenty.utils.extension.printDebug
import uz.behzod.eightytwenty.utils.extension.printFailure
import javax.inject.Inject

@HiltViewModel
class HabitRecommendViewModel @Inject constructor(
    private  val iFetchHabitRecommendsByCategory: FetchHabitRecommendsByCategory
) :
    ReduxViewModel<HabitRecommendState, HabitRecommendAction>(initialState = HabitRecommendState()) {

    fun fetchHabitRecommendsByCategory() {
        val category = currentState.category

        iFetchHabitRecommendsByCategory.invoke(category)
            .onStart { modifyState { state -> state.copy(isLoading = true) }}
            .onEach { habitRecommends ->
                if (habitRecommends.isNotEmpty()) {
                    modifyState { state ->
                        state.copy(
                            habits = habitRecommends,
                            isSuccess = true,
                            isLoading = false,
                            isFailure = false
                        )
                    }

                    printDebug { "Result is $habitRecommends" }
                } else {
                    modifyState { state ->
                        state.copy(
                            isSuccess = false,
                            isLoading = false,
                            isFailure = true
                        )
                    }
                    printFailure { "Fetching data is failed" }
                }
            }
            .launchIn(viewModelScope)
    }

    fun modifyCategory(value: String) {
        modifyState { state -> state.copy(category = value) }
    }

}